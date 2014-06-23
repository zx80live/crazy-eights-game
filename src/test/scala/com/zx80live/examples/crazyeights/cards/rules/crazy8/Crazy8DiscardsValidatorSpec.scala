package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8DiscardsValidatorSpec extends WordSpec with Matchers with Crazy8DiscardsValidator {

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
      validateDiscardByRank(card"5♦".get, Nil) shouldEqual false
      validateDiscardByRank(card"5♦".get, cards"5♦,A♠".get) shouldEqual false
      validateDiscardByRank(card"5♦".get, cards"7♦,8♣".get) shouldEqual false
      validateDiscardByRank(card"5♦".get, cards"5♦,8♠,5♣,5♠".get) shouldEqual false
    }
  }

  "validateDiscardBySuit" when {
    "correct variants" in {
      validateDiscardBySuit(card"4♣".get, cards"4♣".get) shouldEqual true
      validateDiscardBySuit(card"4♣".get, cards"A♣".get) shouldEqual true
      validateDiscardBySuit(card"2♣".get, cards"2♣, 2♦, 2♠".get) shouldEqual true
      validateDiscardBySuit(card"4♣".get, cards"2♦, 2♠, 2♣".get) shouldEqual true
      validateDiscardBySuit(card"4♣".get, cards"A♦, A♣, A♠".get) shouldEqual true
      validateDiscardBySuit(card"2♥".get, cards"2♣, 2♦, 2♠".get) shouldEqual true
    }

    "illegal variants" in {
      validateDiscardBySuit(card"2♥".get, Nil) shouldEqual false
      validateDiscardBySuit(card"2♥".get, cards"4♣, 4♦, 4♠".get) shouldEqual false
      validateDiscardBySuit(card"2♥".get, cards"2♥, 3♥, 4♥".get) shouldEqual false
      validateDiscardBySuit(card"2♥".get, cards"2♥, 3♣, 4♦".get) shouldEqual false
    }
  }

  "validateDiscard" when {
    "correct variants" in {
      // by joker group
      validateDiscard(card"5♦".get, cards"★, ☆, ☆, ★".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"★".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"☆".get) shouldEqual true
      // by eight group
      validateDiscard(card"5♦".get, cards"8♠".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"8♥".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"8♦".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"8♣".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"8♠,8♥,8♦,8♣".get) shouldEqual true
      // by rank group
      validateDiscard(card"5♦".get, cards"5♦".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"5♦,5♣".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"5♦,5♣,5♠".get) shouldEqual true
      validateDiscard(card"5♦".get, cards"5♦,5♣,5♠,5♥".get) shouldEqual true
      // by suit group
      validateDiscard(card"4♣".get, cards"4♣".get) shouldEqual true
      validateDiscard(card"4♣".get, cards"A♣".get) shouldEqual true
      validateDiscard(card"2♣".get, cards"2♣, 2♦, 2♠".get) shouldEqual true
      validateDiscard(card"4♣".get, cards"2♦, 2♠, 2♣".get) shouldEqual true
      validateDiscard(card"4♣".get, cards"A♦, A♣, A♠".get) shouldEqual true
      validateDiscard(card"2♥".get, cards"2♣, 2♦, 2♠".get) shouldEqual true
      // current joker
      validateDiscard(card"☆".get, cards"4♣, 4♦, 4♠".get) shouldEqual true
      validateDiscard(card"☆".get, cards"9♠".get) shouldEqual true

    }

    "illegal variants" in {
      // by joker group
      validateDiscard(card"5♦".get, Nil) shouldEqual false
      validateDiscard(card"5♦".get, cards"2♠".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"A♦,★".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"A♦,☆".get) shouldEqual false
      // by eight group
      validateDiscard(card"5♦".get, Nil) shouldEqual false
      validateDiscard(card"5♦".get, cards"2♠".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"2♠,8♠".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"8♠,8♥,A♦,8♦,8♣".get) shouldEqual false
      // by rank group
      validateDiscard(card"5♦".get, Nil) shouldEqual false
      validateDiscard(card"5♦".get, cards"5♦,A♠".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"7♦,8♣".get) shouldEqual false
      validateDiscard(card"5♦".get, cards"5♦,8♠,5♣,5♠".get) shouldEqual false
      // by suit group
      validateDiscard(card"2♥".get, Nil) shouldEqual false
      validateDiscard(card"2♥".get, cards"4♣, 4♦, 4♠".get) shouldEqual false
      validateDiscard(card"2♥".get, cards"2♥, 3♥, 4♥".get) shouldEqual false
      validateDiscard(card"2♥".get, cards"2♥, 3♣, 4♦".get) shouldEqual false
      // current joker
      validateDiscard(card"☆".get, cards"2♥, 3♥, 4♥".get) shouldEqual false
    }
  }

  "validateFirstJoker" in {
    validateFirstJoker(card"☆".get, cards"4♣, 4♦, 4♠".get) shouldEqual true
    validateFirstJoker(card"☆".get, cards"2♥, 3♥, 4♥".get) shouldEqual false
  }

}
