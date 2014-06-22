package com.zx80live.examples.crazyeights.cards

import com.zx80live.examples.crazyeights.cards.rules.Crazy8ShuffleCondition
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CardsHelperSpec extends WordSpec with Matchers with CardsHelper {
  "get lazy 52-cards deck" in {
    deck52.length shouldEqual 52
  }

  "get lazy 54-cards deck" in {
    deck54.length shouldEqual 54
  }

  "shuffle" when {
    "without conditions" in {
      val shuffled = shuffle(deck54)
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }

    "with crazy8 conditions" in {
      val shuffled = shuffle(deck54, Crazy8ShuffleCondition)
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }
  }
}
