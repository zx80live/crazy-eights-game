package com.zx80live.examples.crazyeights.cards

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.IllegalCardFormatException
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CardSpec extends WordSpec with Matchers {



  "create 54-cards deck" in {
    deck54Pairs map (p => Card(p._1, p._2))
  }

  "create wrong cards" in {
    intercept[IllegalCardFormatException] {
      Card(Rank.BlackJoker, Suit.Diamonds)
    }
    intercept[IllegalCardFormatException] {
      Card(Rank.WhiteJoker, Suit.Diamonds)
    }
  }
}
