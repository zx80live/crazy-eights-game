package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.MoveValidator

/**
 *
 * @author Andrew Proshkin
 */
object Crazy8GameContext {

  implicit val moveValidator = new Crazy8DiscardsValidator with MoveValidator {
    override def validateMove(discard: List[Card], cards: List[Card]): Boolean = {
      validateDiscard(cards.head, discard)
    }
  }
}
