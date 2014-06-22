package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
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

  "discardCards" when {
    "discard empty cards" in {
      val result: Either[DiscardException, Boolean] = new Crazy8Workspace().discardCards(List())
      result shouldBe a[Left[DiscardException, Boolean]]
    }
  }
}
