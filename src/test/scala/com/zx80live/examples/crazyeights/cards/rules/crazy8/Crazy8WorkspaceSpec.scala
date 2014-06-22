package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.Rank
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
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
    "empty cards" in {
      val result: Either[DiscardException, Boolean] = new Crazy8Workspace().discardCards(List())
      result shouldBe a[Left[DiscardException, Boolean]]
    }
  }
}
