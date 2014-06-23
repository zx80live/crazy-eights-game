package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.rules.ReadonlyWorkspace

/**
 *
 * @author Andrew Proshkin
 */
class ConsoleActor extends Actor with ActorLogging {
  var cards: List[Card] = Nil
  var workspace: Option[ReadonlyWorkspace] = None

  override def receive: Receive = {

    case SuccessDiscard(correctDiscardCards, ws, event) =>
      cards = cards diff correctDiscardCards
      workspace = Some(ws)
      logStatus()
      log.info(s"discard event: $event")
      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => sender ! Pass()
      }

    case WrongDiscard(wrongMoveCards, ws, msg) =>
      workspace = Some(ws)
      log.error(s"wrong move $wrongMoveCards because $msg")
      logStatus()
      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => sender ! Pass()
      }

    case DrawedCard(card, ws) =>
      cards = card :: cards
      workspace = Some(ws)
      logStatus()

      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => sender ! Pass()
      }

    case DealAndNextMove(list, ws) =>
      cards = list
      workspace = Some(ws)
      logStatus()

      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => sender ! Pass()
      }

    case NextMove(ws) =>
      workspace = Some(ws)
      logStatus()

      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => sender ! Pass()
      }

    case m@_ => log.error(s"unsupported message $m")
  }

  private def logStatus(): Unit = {
    log.info(s"\n${workspace.toString}")
    log.info(s"\nyour cards: $cards")
  }

  private def enterMoveCards(): Either[Any, List[Card]] = {
    log.info("\nenter pass|p|draw|d|exit|e or comma-separated cards:>")
    scala.io.StdIn.readLine() match {
      case "exit" | "e" =>
        Left(Exit())
      case "draw" | "d" =>
        Left(Draw())
      case "pass" | "p" =>
        Left(Pass())
      case cmd =>
        val parsedCards: Option[List[Card]] = cards"${cmd}"
        parsedCards match {
          case Some(list) =>
            Right(list)
          case _ =>
            log.error("wrong cards or command, try again")
            enterMoveCards()
        }
    }
  }

}
