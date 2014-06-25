package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.MoveValidator
import com.zx80live.examples.crazyeights.cards.dsl.CardsDSL._

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

  implicit class MoveStringCards(left: String) {
    def -->(right: String)(implicit validator: MoveValidator): Boolean = {
      val c1: Option[List[Card]] = cards"$left"
      val c2: Option[List[Card]] = cards"$right"

      (if (c1.isDefined) c1.get else Nil) --> (if (c2.isDefined) c2.get else Nil)
    }
  }

  implicit class MoveCards(left: List[Card]) {
    def -->(right: List[Card])(implicit validator: MoveValidator): Boolean = {
      validator.validateMove(left, right)
    }
  }

}
