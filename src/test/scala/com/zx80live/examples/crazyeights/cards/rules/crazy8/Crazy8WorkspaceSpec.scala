package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DiscardException, EmptyCardsDiscardException}
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8WorkspaceSpec extends WordSpec with Matchers with CardsHelper with Crazy8WorkspaceBuilder {


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

  "discardCards" when {
    "discard empty cards" in {
      val ws = new Crazy8Workspace
      val result: Either[DiscardException, Boolean] = ws.discardCards(List())
      result shouldBe a[Left[DiscardException, Boolean]]
      result match {
        case Left(t: EmptyCardsDiscardException) => true shouldEqual true //TODO refactoring
        case _ => true shouldEqual false //TODO refactoring
      }
    }
  }

  "discard validation" when {
    "validateDiscardByJoker" in {
    }
  }
}
