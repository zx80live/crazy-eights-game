package com.zx80live.examples.crazyeights.actors.newgame

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.DiscardEvent

/**
 *
 * @author Andrew Proshkin
 */
object Messages {

  case class Deal(cards: List[Card])

  case class NextMove(currentCard: Card)

  case class DrawResult(drawCard: Option[Card] = None, currentCard: Card, msg: String = "")

  case class DiscardResult(current: Card, cards: List[Card], evt: DiscardEvent)

  case class Pass(msg: String = "")

  case class EmptyCards()

}
