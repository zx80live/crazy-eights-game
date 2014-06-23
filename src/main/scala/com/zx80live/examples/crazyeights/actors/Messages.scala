package com.zx80live.examples.crazyeights.actors

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.{ReadonlyWorkspace, Workspace}

/**
 * Messages for actors
 *
 * @author Andrew Proshkin
 */
object Messages {

  case class DealAndNextMove(cards: List[Card], workspace: ReadonlyWorkspace)

  case class CreateHumanPlayer()

  case class WorkspaceStatus()

  /**
   * Some broadcast message
   *
   * @param msg - message text
   */
  case class BroadcastMessage(msg: String)

  /**
   * Initiate new game and send cards for all players
   *
   * to: Master
   *
   * @param playersCount - players count
   */
  case class NewGame(playersCount: Int = 2)

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

  case class Draw()

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
  case class NextMove(workspace: Workspace)

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

  case class Deal(cards: List[Card])

  /**
   * Response for GetCard message
   *
   * send from: Master
   * to: Player
   */
  //type Card = com.zx80live.examples.crazyeights.cards.Card

}
