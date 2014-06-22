package com.zx80live.examples.crazyeights.cards

import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8ShuffleCondition
import org.scalatest.{Matchers, WordSpec}
import com.zx80live.examples.crazyeights.cards.CardsHelper._

/**
 *
 * @author Andrew Proshkin
 */
class CardsHelperSpec extends WordSpec with Matchers {
  "get lazy 52-cards deck" in {
    deck52.length shouldEqual 52
  }

  "get lazy 54-cards deck" in {
    deck54.length shouldEqual 54
  }

  "shuffleDeck" when {
    "without conditions" in {
      val shuffled = shuffleDeck(deck54)
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }

    "with crazy8 conditions" in {
      val shuffled = shuffleDeck(deck54, Crazy8ShuffleCondition)
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }
  }

  "List[Card] shuffle implicitly" when {
    "without conditions implicitly" in {
      val shuffled = deck54.shuffle
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }

    "with crazy8 conditions implicitly" in {
      val shuffled = deck54.shuffle(Crazy8ShuffleCondition)
      shuffled.length shouldEqual deck54.length
      shuffled foreach (deck54.contains(_))
    }
  }
}
