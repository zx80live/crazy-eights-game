package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.dsl.Rank
import com.zx80live.examples.crazyeights.cards.dsl.Suit
import com.zx80live.examples.crazyeights.cards.dsl._
import CardsHelper._
import Rank._
import Suit._
import com.zx80live.examples.crazyeights.cards.dsl.CardsDSL._
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8WorkspaceBuilderSpec extends WordSpec with Matchers with Crazy8WorkspaceBuilder {
  "createWorkspace" when {
    "from standard 54-cards deck" in {
      val cards: List[Card] = deck54
      checkCorrectWorkspace(cards, createWorkspace(cards))
    }

    "from correct list" in {
      val cards: List[Card] = cards" 2♦, ★, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get
      checkCorrectWorkspace(cards, createWorkspace(cards))
    }

    "from empty list" in {
      val ws: Either[Throwable, (List[Card], List[Card])] = createWorkspace(Nil)
      ws shouldBe a[Left[Throwable, (List[Card], List[Card])]]
    }

    "from list of one element" in {
      val ws: Either[Throwable, (List[Card], List[Card])] = createWorkspace(cards"A♦".get)
      ws shouldBe a[Left[Throwable, (List[Card], List[Card])]]
    }
  }

  private def checkCorrectWorkspace(cards: List[Card], ws: Either[Throwable, (List[Card], List[Card])]): Unit = {
    ws shouldBe a[Right[Throwable, (List[Card], List[Card])]]
    ws match {
      case Right((stockpile, discardPile)) =>
        stockpile.length shouldEqual (cards.length - 1)
        discardPile.length shouldEqual 1
        stockpile.contains(discardPile.head) shouldBe false
        discardPile.head.rank should not be Eight
        discardPile.head.suit should not be Special
      case _ => true shouldBe false //TODO refactoring
    }
  }
}
