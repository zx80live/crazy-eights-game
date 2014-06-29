package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsDSL._
import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
import com.zx80live.examples.crazyeights.cards._
import com.zx80live.examples.crazyeights.cards.rules.WorkspaceEventListener
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DealException, DiscardException}
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8WorkspaceSpec extends WordSpec with Matchers with Crazy8WorkspaceBuilder {

  "workspace" when {
    "init workspace" in {
      val ws = new Crazy8Workspace
      ws.stockPile.length shouldEqual (deck54.length - 1)
      ws.discardPile.length shouldEqual 1
      ws.currentCard shouldEqual ws.discardPile.head
      ws.stockPile.contains(ws.currentCard) shouldEqual false
      ws.currentCard.rank should not be Eight
      ws.currentCard.suit should not be Special
    }
  }

  "workspace deal" when {
    "deal for incorrect players count" in {
      new Crazy8Workspace().deal(-100) shouldBe a[Left[DealException, List[List[Card]]]]
      new Crazy8Workspace().deal(0) shouldBe a[Left[DealException, List[List[Card]]]]
      new Crazy8Workspace().deal(100) shouldBe a[Left[DealException, List[List[Card]]]]
    }

    "deal for correct players count = 1" in {
      val playersCount = 1
      val ws = new Crazy8Workspace(shuffle = false)

      val topCards = ws.stockPile.take(ws.dealCardsCount)

      val deal: Either[DealException, List[List[Card]]] = ws.deal(playersCount)
      deal shouldBe a[Right[DealException, List[List[Card]]]]

      deal match {
        case Right(list: List[List[Card]]) =>
          list.length shouldEqual playersCount
          list.head.length shouldEqual ws.dealCardsCount

          list.head.reverse shouldEqual topCards
        case _ =>
          true shouldEqual false
      }
    }

    //TODO deal for correct players count = 2,3,...n
  }

  "discardCards - correct variants" when {
    "jokers" in {
      new Crazy8Workspace().discardCards(cards"★, ☆, ☆, ★".get) shouldBe a[Right[DiscardException, JokerDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"★".get) shouldBe a[Right[DiscardException, JokerDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"☆".get) shouldBe a[Right[DiscardException, JokerDiscardEvent]]
    }

    "eights" in {
      new Crazy8Workspace().discardCards(cards"8♠".get) shouldBe a[Right[DiscardException, EightDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"8♥".get) shouldBe a[Right[DiscardException, EightDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"8♦".get) shouldBe a[Right[DiscardException, EightDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"8♣".get) shouldBe a[Right[DiscardException, EightDiscardEvent]]
      new Crazy8Workspace().discardCards(cards"8♠,8♥,8♦,8♣".get) shouldBe a[Right[DiscardException, EightDiscardEvent]]
    }

    "rank 1" in {
      val ws = new Crazy8Workspace
      val current: Rank.Value = ws.currentCard.rank
      ws.discardCards(cards"$current♦".get) shouldBe a[Right[DiscardException, SuccessDiscardEvent]]
    }

    "rank 2" in {
      val ws = new Crazy8Workspace
      val r = ws.currentCard.rank
      ws.discardCards(cards"$r♦, $r♣".get) shouldBe a[Right[DiscardException, SuccessDiscardEvent]]
    }

    "rank 3" in {
      val ws = new Crazy8Workspace
      val r = ws.currentCard.rank
      ws.discardCards(cards"$r♦, $r♣, $r♠".get) shouldBe a[Right[DiscardException, SuccessDiscardEvent]]
    }

    //TODO (for suit-xxx) create test data with filtering current
    "suit 1" in {
      val ws = new Crazy8Workspace
      val s = ws.currentCard.suit
      ws.discardCards(cards"4$s".get) shouldBe a[Right[DiscardException, SuccessDiscardEvent]]
    }

    "suit 2" in {
      val ws = new Crazy8Workspace
      val s = ws.currentCard.suit
      ws.discardCards(cards"4$s, 4♣, 4♦, 4♠".get) shouldBe a[Right[DiscardException, SuccessDiscardEvent]]
    }
  }

  "discardCards - illegal variants" when {
    //TODO add test cases
    "illegal 1" in {
      new Crazy8Workspace().discardCards(List()) shouldBe a[Left[DiscardException, DiscardEvent]]
      new Crazy8Workspace().discardCards(cards"A♦, ★".get) shouldBe a[Left[DiscardException, DiscardEvent]]
      new Crazy8Workspace().discardCards(cards"A♦, ☆".get) shouldBe a[Left[DiscardException, DiscardEvent]]
      new Crazy8Workspace().discardCards(cards"5♦,8♠,5♣,5♠".get) shouldBe a[Left[DiscardException, DiscardEvent]]
    }
  }

  "drawCard" when {
    "recreate stockpile event from discard pile" in {
      val ws = new Crazy8Workspace(cards"2♦,3♠,4♣".get, shuffle = false)
      ws.currentCard shouldEqual card"2♦".get

      ws.discardCards(cards"10♦".get)
      ws.discardCards(cards"J♦".get)
      ws.discardCards(cards"Q♦".get)
      ws.discardCards(cards"K♦".get)
      ws.discardCards(cards"A♦".get)

      ws.currentCard shouldEqual card"A♦".get

      var event: Option[WorkspaceEvent] = None
      implicit val listener = new WorkspaceEventListener {
        override def onEvent(evt: WorkspaceEvent): Unit = {
          event = Some(evt)
        }
      }

      ws.drawCard shouldEqual card"3♠"
      /**/ event shouldEqual None
      ws.drawCard shouldEqual card"4♣"
      /**/ event shouldEqual None
      ws.currentCard shouldEqual card"A♦".get
      /**/ event shouldEqual None
      ws.drawCard shouldEqual card"K♦" // WorkspaceEvent
      /**/ event should not equal None
      ws.drawCard shouldEqual card"Q♦"
      /**/ event should not equal None
      ws.drawCard shouldEqual card"J♦"
      /**/ event should not equal None
      ws.drawCard shouldEqual card"10♦"
      /**/ event should not equal None
      ws.drawCard shouldEqual card"2♦"
      /**/ event should not equal None
      ws.drawCard shouldEqual None
      /**/ event should not equal None
      ws.drawCard shouldEqual None
    }

    "stack overflow" in {

    }
  }

  "setCurrentSuit" when {
    "correct cases" in {
      new Crazy8Workspace(cards"8♦,2♦,4♣".get, false).setCurrentSuit(Suit.Hearts) match {
        case Right(true) => true shouldEqual true
        case _ => true shouldEqual false
      }
    }
    "incorrect cases" in {
      new Crazy8Workspace(cards"2♦,8♦,4♣".get, false).setCurrentSuit(Suit.Hearts) match {
        case Left(e) => true shouldEqual true
        case _ => true shouldEqual false
      }
    }
  }
}
