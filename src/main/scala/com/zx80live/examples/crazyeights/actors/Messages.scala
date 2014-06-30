package com.zx80live.examples.crazyeights.actors

import com.zx80live.examples.crazyeights.cards.rules.crazy8.DiscardEvent
import com.zx80live.examples.crazyeights.cards.{Card, Suit}

/**
 *
 * @author Andrew Proshkin
 */
object Messages {

  case class Deal(cards: List[Card])

  case class NextMove(currentCard: Card)

  case class Discard(cards: List[Card])

  case class SetSuit(suit: Option[Suit.Value])

  case class Draw()

  case class DrawResult(drawCard: Option[Card] = None, currentCard: Card, msg: String = "")

  case class DiscardResult(current: Card, cards: List[Card], evt: DiscardEvent)

  case class Pass()

  case class EmptyCards()

  case class Exit()

  case class NewGame(count: Int = 2)

}
