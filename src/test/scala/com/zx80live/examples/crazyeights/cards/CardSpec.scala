package com.zx80live.examples.crazyeights.cards

import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CardSpec extends WordSpec with Matchers with CardsHelper {



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
