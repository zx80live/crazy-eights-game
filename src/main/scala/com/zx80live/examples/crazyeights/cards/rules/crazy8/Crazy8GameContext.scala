package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.CardsDSL._
import com.zx80live.examples.crazyeights.cards.rules.DiscardValidator

/**
 *
 * @author Andrew Proshkin
 */
object Crazy8GameContext {

  implicit val moveValidator = new Crazy8DiscardsValidator {}

  implicit class MoveStringCards(left: String) {
    def -->(right: String)(implicit validator: DiscardValidator): Boolean = {
      val discard: Option[List[Card]] = cards"$left"
      val current: Option[Card] = card"$right"

      if (discard.isDefined && current.isDefined)
        discard.get --> current.get
      else
        false
    }
  }

  implicit class MoveCards(left: List[Card]) {
    def -->(right: Card)(implicit validator: DiscardValidator): Boolean = {
      validator.validateDiscard(right, left)
    }
  }

}
