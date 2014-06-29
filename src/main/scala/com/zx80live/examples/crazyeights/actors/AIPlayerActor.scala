package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.ReadonlyWorkspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, JokerDiscardEvent, SuccessDiscardEvent}
import com.zx80live.examples.crazyeights.util.PrettyListView

/**
 * AI player actor
 *
 * TODO show AIPlayer's cards for debug mode = enabled
 * TODO change preferred suit if AI has moved with eitht
 *
 * @author Andrew Proshkin
 */
class AIPlayerActor extends Actor with Crazy8MovePatterns with ActorLogging with PrettyListView {
  var workspace: Option[ReadonlyWorkspace] = None
  var cards: List[Card] = Nil

  override def receive = {
    case Deal(list, ws) =>
      cards = list
      workspace = Some(ws)
      log.info(s"accept deal cards: ${prettyList(list)}")

    case SuccessDiscard(correctDiscardCards, ws, event) =>
      cards = cards diff correctDiscardCards
      workspace = Some(ws)
      log.info(s"discard event: $event")
      event match {
        case _: JokerDiscardEvent => None
          log.info("pass move because there was joker")
          sender ! Pass(Some("WithJoker"))

        case _: SuccessDiscardEvent =>
          movePreferred()
        case _ => sender ! Pass()
      }

    case WrongDiscard(wrongMoveCards, ws, msg) =>
      workspace = Some(ws)
      log.error(s"wrong move $wrongMoveCards because $msg")
      sender ! Pass(Some("AI has moved illegal"))

    case DrawedCard(card, ws) =>
      cards = card :: cards
      workspace = Some(ws)
      movePreferred()

    case NextMove(ws, true) =>
      log.info("player can move any card because there is joker")
      workspace = Some(ws)
      movePreferred()

    case NextMove(ws, false) =>
      workspace = Some(ws)
      movePreferred()



    case m@_ => log.info(s"accept unsupported message $m")
  }

  private def movePreferred() = {
    findPreferred(workspace.get.currentCard, cards) match {
      case xs if xs.length > 0 =>
        log.info(s"request: ${prettyList(cards)} --[discard]--> ${prettyList(xs)}")
        sender ! Discard(xs)
      case _ =>
        log.info(s"draw request")
        sender ! Draw()
    }
  }

}
