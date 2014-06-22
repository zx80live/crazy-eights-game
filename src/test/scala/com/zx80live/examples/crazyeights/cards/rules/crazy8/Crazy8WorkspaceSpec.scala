package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.Rank
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.rules.WorkspaceEventListener
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException
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

  "discardCards - correct variants" when {
    "jokers" in {
      new Crazy8Workspace().discardCards(cards"★, ☆, ☆, ★".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"★".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"☆".get) shouldEqual Right(true)
    }

    "eights" in {
      new Crazy8Workspace().discardCards(cards"8♠".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"8♥".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"8♦".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"8♣".get) shouldEqual Right(true)
      new Crazy8Workspace().discardCards(cards"8♠,8♥,8♦,8♣".get) shouldEqual Right(true)
    }

    "rank 1" in {
      val ws = new Crazy8Workspace
      val current: Rank.Value = ws.currentCard.rank
      ws.discardCards(cards"$current♦".get) shouldEqual Right(true)
    }

    "rank 2" in {
      val ws = new Crazy8Workspace
      val r = ws.currentCard.rank
      ws.discardCards(cards"$r♦, $r♣".get) shouldEqual Right(true)
    }

    "rank 3" in {
      val ws = new Crazy8Workspace
      val r = ws.currentCard.rank
      ws.discardCards(cards"$r♦, $r♣, $r♠".get) shouldEqual Right(true)
    }

    //TODO (for suit-xxx) create test data with filtering current
    "suit 1" in {
      val ws = new Crazy8Workspace
      val s = ws.currentCard.suit
      ws.discardCards(cards"4$s".get) shouldEqual Right(true)
    }

    "suit 2" in {
      val ws = new Crazy8Workspace
      val s = ws.currentCard.suit
      ws.discardCards(cards"4$s, 4♣, 4♦, 4♠".get) shouldEqual Right(true)
    }
  }

  "discardCards - illegal variants" when {
    //TODO add test cases
    "illegal 1" in {
      new Crazy8Workspace().discardCards(List()) shouldBe a[Left[DiscardException, Boolean]]
      new Crazy8Workspace().discardCards(cards"A♦, ★".get) shouldBe a[Left[DiscardException, Boolean]]
      new Crazy8Workspace().discardCards(cards"A♦, ☆".get) shouldBe a[Left[DiscardException, Boolean]]
      new Crazy8Workspace().discardCards(cards"5♦,8♠,5♣,5♠".get) shouldBe a[Left[DiscardException, Boolean]]
    }
  }

  "drawCard" when {
    "recreate stockpile from discard pile" in {
      //TODO test case
      val ws = new Crazy8Workspace(cards"2♦,3♠,4♣".get)
      println(ws)

      ws.discardCards(cards"${ws.currentCard.rank}♣".get)
      ws.discardCards(cards"9♣".get)
      ws.discardCards(cards"10♣".get)
      ws.discardCards(cards"J♣".get)
      ws.discardCards(cards"Q♣".get)
      ws.discardCards(cards"K♣".get)
      ws.discardCards(cards"A♣".get)
      println(ws)



      implicit val listener = new WorkspaceEventListener {
        override def onEvent[T](evt: WorkspaceEvent[T]): Unit = println(s"onEvent($evt)")
      }

      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard) // event
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)
      println(ws.drawCard)

    }
  }
}
