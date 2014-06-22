package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card

/**
 * Condition for cards shuffle
 *
 * @author Andrew Proshkin
 */
trait ShuffleCondition {
  def acceptShuffle(deck: List[Card]): Boolean
}
