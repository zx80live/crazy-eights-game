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

  "findPreferredByRank" in {
    findPreferredByRank(card"A♠".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(Some(cards"A♦, A♣".get))
    findPreferredByRank(card"2♥".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(Some(cards"2♦, 2♣, 2♠".get))
    findPreferredByRank(card"2♣".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(Some(cards"2♦, 2♣, 2♠".get))
    findPreferredByRank(card"7♣".get, cards" 2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆ ".get) should equal(None)
  }

  "findPreferredBySuit" in {

  }
}
