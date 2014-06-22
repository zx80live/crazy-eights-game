package com.zx80live.examples.crazyeights.actors

/**
 * Messages for actors
 *
 * @author Andrew Proshkin
 */
object Messages {


  /**
   * Some broadcast message
   *
   * @param msg - message text
   */
  case class BroadcastMessage(msg: String)

  /**
   * Initiate new game and send cards for all players
   *
   * send from: Master
   * to: Players
   *
   * @param cards - player's cards
   */
  case class NewGame(cards: List[Card])

  /**
   * Cards are moved from player
   *
   * send from: Player
   * to: Master
   *
   * @param cards - cards
   */
  case class Discard(cards: List[Card])

  /**
   * Pass move with optional message
   *
   * for skip move:
   * send from: Player
   * to: Master
   *
   * for deny move:
   * send from: Master
   * to: Player
   *
   * @param msg - optional message text
   */
  case class Pass(msg: Option[String] = None)

  /**
   * Request for new card
   *
   * send from: Player
   * to: Master
   */
  case class GetCard()

  /**
   * Initiate next move for player
   *
   * send from: Master
   * to: Player
   */
  case class NextMove(currentCard: Card)

  /**
   * Broadcast message with some user move
   *
   * send from: Master
   * to: Players
   *
   * @param cards - current card
   */
  case class UserMove(current: Card, cards: List[Card])

  /**
   * Send EmptyCards if player's cards are empty
   *
   * send from: Player
   * to: Master
   */
  case class EmptyCards()

  /**
   * Broadcast message
   *
   * send from: Master
   * to: Players
   *
   * @param card - current card
   */
  case class CurrentCard(card: Card)

  /**
   * Response for GetCard message
   *
   * send from: Master
   * to: Player
   */
  type Card = com.zx80live.examples.crazyeights.cards.Card

}