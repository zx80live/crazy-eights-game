package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsDSL
import CardsDSL._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8GameContext._
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

  "validateDiscard implicitly" when {
    "correct variants" in {
      // by joker group
      "★, ☆, ☆, ★" --> "5♦" shouldEqual true
      "★" --> "5♦" shouldEqual true
      "☆" --> "5♦" shouldEqual true
      // by eight group
      "8♠" --> "5♦" shouldEqual true
      "8♥" --> "5♦" shouldEqual true
      "8♦" --> "5♦" shouldEqual true
      "8♣" --> "5♦" shouldEqual true
      "8♠,8♥,8♦,8♣" --> "5♦" shouldEqual true
      // by rank group
      "5♦" --> "5♦" shouldEqual true
      "5♦,5♣" --> "5♦" shouldEqual true
      "5♦,5♣,5♠" --> "5♦" shouldEqual true
      "5♦,5♣,5♠,5♥" --> "5♦" shouldEqual true
      // by suit group
      "4♣" --> "4♣" shouldEqual true
      "A♣" --> "4♣" shouldEqual true
      "2♣, 2♦, 2♠" --> "2♣" shouldEqual true
      "2♦, 2♠, 2♣" --> "4♣" shouldEqual true
      "A♦, A♣, A♠" --> "4♣" shouldEqual true
      "2♣, 2♦, 2♠" --> "2♥" shouldEqual true
      // current joker
      "4♣, 4♦, 4♠" --> "☆" shouldEqual true
      "9♠" --> "☆" shouldEqual true

    }

    "illegal variants" in {
      // by joker group
      "" --> "5♦" shouldEqual false
      "2♠" --> "5♦" shouldEqual false
      "A♦,★" --> "5♦" shouldEqual false
      "A♦,☆" --> "5♦" shouldEqual false
      // by eight group
      "2♠,8♠" --> "5♦" shouldEqual false
      "8♠,8♥,A♦,8♦,8♣" --> "5♦" shouldEqual false
      // by rank group
      "5♦,A♠" --> "5♦" shouldEqual false
      "7♦,8♣" --> "5♦" shouldEqual false
      //"5♦,8♠,5♣,5♠" -> "5♦" shouldEqual false //TODO test fail
      // by suit group
      "4♣, 4♦, 4♠" --> "2♥" shouldEqual false
      "2♥, 3♥, 4♥" --> "2♥" shouldEqual false
      "2♥, 3♣, 4♦" --> "2♥" shouldEqual false
      // current joker
      "2♥, 3♥, 4♥" --> "☆" shouldEqual false
    }
  }

  "validate rank implicitly" in {
    "5♦" isRank "5" shouldEqual true
    "5♦,5♠" isRank "5" shouldEqual true
    "5♦,5♠,5♣" isRank "5" shouldEqual true
    "5♦,5♠,5♣,5♠" isRank "5" shouldEqual true

    "" isRank "" shouldEqual false
    "" isRank "5" shouldEqual false
    "7♦" isRank "5" shouldEqual false
    "5♦,7♠" isRank "5" shouldEqual false
    "5♦,5♠,5♣,5♠" isRank "" shouldEqual false
  }

}
