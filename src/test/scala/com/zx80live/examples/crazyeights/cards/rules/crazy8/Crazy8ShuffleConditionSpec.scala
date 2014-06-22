package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8ShuffleConditionSpec extends WordSpec with Matchers with CardsHelper {

  import com.zx80live.examples.crazyeights.cards.Rank._
  import com.zx80live.examples.crazyeights.cards.Suit._
  import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
  import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8ShuffleCondition._

  "acceptShuffle" when {
    "true condition for cards except Eights and Jokers at first position in deck" in {
      deck54.filter(c => c.suit != Special && c.rank != Eight) foreach { card =>
        acceptShuffle(card :: cards"3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) shouldEqual true
      }
    }

    "false condition for Eights and Jokers at first position in deck" in {
      acceptShuffle(cards" 8♠, 2♦, ★, 3♣, 2♣, A♦, 2♠, ☆ ".get) shouldEqual false
      acceptShuffle(cards" 8♥, 2♦, ★, 3♣, 2♣, A♦, 2♠, ☆ ".get) shouldEqual false
      acceptShuffle(cards" 8♦, 2♦, ★, 3♣, 2♣, A♦, 2♠, ☆ ".get) shouldEqual false
      acceptShuffle(cards" 8♣, 2♦, ★, 3♣, 2♣, A♦, 2♠, ☆ ".get) shouldEqual false
      acceptShuffle(cards" ★, 2♦, 8♣, 3♣, 2♣, A♦, 2♠, ☆ ".get) shouldEqual false
      acceptShuffle(cards" ☆ , 2♦, ★, 3♣, 2♣, A♦, 2♠, 8♣".get) shouldEqual false
    }
  }
}
