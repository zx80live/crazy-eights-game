package com.zx80live.examples.crazyeights.cards.dsl

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._

/**
 *
 * @author Andrew Proshkin
 */
object CardsDSL {

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
