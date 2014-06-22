package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card

/**
 *
 * @author Andrew Proshkin
 */
trait DiscardValidator {
  def validateDiscard(current: Card, cards: List[Card]): Boolean
}
