package com.zx80live.examples.crazyeights.model

import com.zx80live.examples.crazyeights.model.ConversionUtils._
import org.scalatest._

/**
 * Specification for MovePatterns
 *
 * @author Andrew Proshkin
 */
class MovePatternsSpec extends WordSpec with Matchers with MovePatterns {

  "findEight" when {
    "find first Some(Rank.Eight) or None" in {
      findEight(cards" 2♦, ★, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) shouldEqual card"8♦"
      findEight(cards" 2♦, ★, 3♣, 2♣, A♦, J♦, K♠, 2♠, A♣, ☆ ".get) shouldEqual None
    }
  }

  "findJoker" when {
    "find first Some(Rank.XXXJoker) or None" in {
      findJoker(cards" 2♦,  ★, 3♣, 2♣, A♦, 8♦, 8♠, A♣, ☆ ".get) shouldEqual card"★"
      findJoker(cards" 2♦, 5♣, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣".get) shouldEqual None
    }
  }

  "findPreferredByRank without eights and jokers and sorted by priority" in {
    findPreferredByRank(card"A♠".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(cards"A♦, A♣")
    findPreferredByRank(card"2♥".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(cards"2♦, 2♣, 2♠")
    findPreferredByRank(card"2♣".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(cards"2♦, 2♣, 2♠")
    findPreferredByRank(card"7♣".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(None)
  }

  "findPreferredBySuit without eights and jokers and sorted by priority" in {
    findPreferredBySuit(card"4♣".get, cards"2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆".get) should equal(List(cards"2♣, 2♦, 2♠".get, cards"A♣, A♦".get, cards"3♣".get))
    findPreferredBySuit(card"4♦".get, cards"2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆".get) should equal(List(cards"2♦, 2♣, 2♠".get, cards"A♦, A♣".get))
    findPreferredBySuit(card"7♠".get, cards"2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆".get) should equal(List(cards"2♠, 2♦, 2♣".get))
  }

  "findPreferred" when {
    "not moves" in {
      findPreferred(card"4♥".get, cards"2♦, 3♣, 2♣, A♦, 2♠, A♣".get) should equal(Nil)
    }

    "best move - eight" in {
      findPreferred(card"4♥".get, cards"2♦, 3♣, 2♣, A♦, 8♦, 2♠, A♣".get) should equal(cards"8♦".get)
      findPreferred(card"4♥".get, cards"2♦, 3♣, 8♣, A♦, 8♦, 2♠, A♣".get) should equal(cards"8♣".get)
    }

    "best move - joker" in {
      findPreferred(card"4♥".get, cards"2♦, 3♣, 2♣, A♦, 2♠, ☆, 2♠, A♣".get) should equal(cards"☆".get)
    }

    "best move - by current suit" in {
      findPreferred(card"A♠".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(cards"2♠, 2♦, 2♣".get)
    }

    "best move - by current rank" in {
      findPreferred(card"2♥".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(cards"2♦, 2♣, 2♠".get)
    }
  }
}
