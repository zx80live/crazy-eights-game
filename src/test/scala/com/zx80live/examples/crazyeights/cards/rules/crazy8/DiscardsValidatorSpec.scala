package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class DiscardsValidatorSpec extends WordSpec with Matchers with DiscardsValidator {

  //♠ ♥ ♦ ♣ ★ ☆
  "validateDiscardByJoker" when {

    "correct variants" in {
      validateDiscardByJoker(cards"★, ☆, ☆, ★".get) shouldEqual true
      validateDiscardByJoker(cards"★".get) shouldEqual true
      validateDiscardByJoker(cards"☆".get) shouldEqual true
    }

    "illegal variants" in {
      validateDiscardByJoker(Nil) shouldEqual false
      validateDiscardByJoker(cards"2♠".get) shouldEqual false
      validateDiscardByJoker(cards"A♦,★".get) shouldEqual false
      validateDiscardByJoker(cards"A♦,☆".get) shouldEqual false
    }
  }

  "validateDiscardByEight" when {
    "correct variants" in {
      validateDiscardByEight(cards"8♠".get) shouldEqual true
      validateDiscardByEight(cards"8♥".get) shouldEqual true
      validateDiscardByEight(cards"8♦".get) shouldEqual true
      validateDiscardByEight(cards"8♣".get) shouldEqual true
      validateDiscardByEight(cards"8♠,8♥,8♦,8♣".get) shouldEqual true
    }

    "illegal variants" in {
      validateDiscardByEight(Nil) shouldEqual false
      validateDiscardByEight(cards"2♠".get) shouldEqual false
      validateDiscardByEight(cards"2♠,8♠".get) shouldEqual false
      validateDiscardByEight(cards"8♠,8♥,A♦,8♦,8♣".get) shouldEqual false
    }
  }

  "validateDiscardByRank" when {
    "correct variants" in {
      validateDiscardByRank(card"5♦".get, cards"5♦".get) shouldEqual true
      validateDiscardByRank(card"5♦".get, cards"5♦,5♣".get) shouldEqual true
      validateDiscardByRank(card"5♦".get, cards"5♦,5♣,5♠".get) shouldEqual true
      validateDiscardByRank(card"5♦".get, cards"5♦,5♣,5♠,5♥".get) shouldEqual true
    }

    "illegal variants" in {
      validateDiscardByRank(card"5♦".get, cards"5♦,A♠".get) shouldEqual false
      validateDiscardByRank(card"5♦".get, cards"7♦,8♣".get) shouldEqual false
      validateDiscardByRank(card"5♦".get, cards"5♦,8♠,5♣,5♠".get) shouldEqual false
      validateDiscardByRank(card"5♦".get, Nil) shouldEqual false
    }
  }

  "validateDiscardBySuit" when {
    "correct variants" in {
    }

    "illegal variants" in {
    }
  }

}
