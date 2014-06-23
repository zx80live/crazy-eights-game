package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.rules.ReadonlyWorkspace

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @author Andrew Proshkin
 */
class ConsoleActor extends Actor with ActorLogging {
  var cards: List[Card] = Nil
  var workspace: Option[ReadonlyWorkspace] = None

  override def receive: Receive = {
    case DrawedCard(card, ws) =>
      cards = card :: cards
      workspace = Some(ws)
      log.info(s"\n${ws.toString}")
      log.info(s"\nyour cards: $cards")

      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => Future({
          Pass()
        }) pipeTo sender
      }

    case DealAndNextMove(list, ws) =>
      cards = list
      workspace = Some(ws)
      log.info(s"\n${ws.toString}")
      log.info(s"\nyour cards: $cards")

      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => Future({
          Pass()
        }) pipeTo sender
      }

    case NextMove(ws) =>
      workspace = Some(ws)
      enterMoveCards() match {
        case Left(cmd) =>
          sender ! cmd

        case Right(moveCards) =>
          sender ! Discard(moveCards)

        case _ => Future({
          Pass()
        }) pipeTo sender
      }

    case m@_ => log.error(s"unsupported message $m")
  }

  private def enterMoveCards(): Either[Any, List[Card]] = {
    log.info("\nyour-move[enter pass|draw or comma-separated cards]:>")
    scala.io.StdIn.readLine() match {
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
