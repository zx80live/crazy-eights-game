package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}

/**
 *
 * @author Andrew Proshkin
 */
object Crazy8ShuffleCondition extends ShuffleCondition {
  override def acceptShuffle(deck: List[Card]): Boolean = deck match {
    case (Card(_, Suit.Special) | Card(Rank.Eight, _)) :: tail => false
    case _ => true
  }
}
