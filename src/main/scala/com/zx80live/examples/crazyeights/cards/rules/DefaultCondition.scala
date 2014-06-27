package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.dsl.{ShuffleCondition, Card}

/**
 * Default cards deck shuffle condition
 *
 * @author Andrew Proshkin
 */
object DefaultCondition extends ShuffleCondition {
  override def acceptShuffle(deck: List[Card]): Boolean = true
}
