package com.zx80live.examples.crazyeights.cards

import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CardSpec extends WordSpec with Matchers with CardsHelper {

  "get lazy 52-cards deck" in {
    deck52.length shouldEqual 52
  }

  "get lazy 54-cards deck" in {
    deck54.length shouldEqual 54
  }

  "create 54-cards deck" in {
    deck54Pairs map (p => Card(p._1, p._2))
  }

  "create wrong cards" in {
    intercept[IllegalArgumentException] {
      Card(Rank.BlackJoker, Suit.Diamonds)
    }
    intercept[IllegalArgumentException] {
      Card(Rank.WhiteJoker, Suit.Diamonds)
    }
  }
}
