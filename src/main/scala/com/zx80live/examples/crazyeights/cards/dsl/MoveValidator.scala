package com.zx80live.examples.crazyeights.cards.dsl

import com.zx80live.examples.crazyeights.cards.Card

/**
 *
 * @author Andrew Proshkin
 */
trait MoveValidator {
  def validateMove(discard: List[Card], cards: List[Card]): Boolean
}
