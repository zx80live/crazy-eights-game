package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class DiscardsValidatorSpec extends WordSpec with Matchers with DiscardsValidator {

  //♠ ♥ ♦ ♣ ★ ☆
  "validateDiscardByJoker" when {

    "correct variants" in {
      validateDiscardByJoker(cards"★, ☆, ☆, ★".get) shouldEqual Right(true)
      validateDiscardByJoker(cards"★".get) shouldEqual Right(true)
      validateDiscardByJoker(cards"☆".get) shouldEqual Right(true)
    }

    "illegal variants" in {
      validateDiscardByJoker(Nil) shouldBe a[Left[DiscardException, Boolean]]
      validateDiscardByJoker(cards"2♠".get) shouldBe a[Left[DiscardException, Boolean]]
      validateDiscardByJoker(cards"A♦,★".get) shouldBe a[Left[DiscardException, Boolean]]
      validateDiscardByJoker(cards"A♦,☆".get) shouldBe a[Left[DiscardException, Boolean]]
    }
  }

  "validateDiscardByEight" when {
    "correct variants" in {
      validateDiscardByEight(cards"8♠".get) shouldEqual Right(true)
      validateDiscardByEight(cards"8♥".get) shouldEqual Right(true)
      validateDiscardByEight(cards"8♦".get) shouldEqual Right(true)
      validateDiscardByEight(cards"8♣".get) shouldEqual Right(true)
      validateDiscardByEight(cards"8♠,8♥,8♦,8♣".get) shouldEqual Right(true)
    }
  }
}
