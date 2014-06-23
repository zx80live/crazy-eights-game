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
    case DealAndNextMove(list, ws) =>
      cards = list
      workspace = Some(ws)
      log.info(ws.toString)
      log.info(s"your cards: ${cards.toString()}")

      enterMoveCards() match {
        case Left(cmd) =>
          Future({
            cmd
          }) pipeTo sender

        case Right(moveCards) =>
          Future({
            Discard(moveCards)
          }) pipeTo sender

        case _ => Future({
          Pass()
        }) pipeTo sender

      }

    case NextMove(ws) =>
      scala.io.StdIn.readLine("human-command:>") match {
        case cmd@_ =>
          val f = Future({
            Some(cmd)
          }) recover {
            case t =>
              log.error(s"can't get command $t")
              None
          }
          f pipeTo sender
      }

    case m@_ => log.error(s"unsupported message $m")
  }

  private def enterMoveCards(): Either[Any, List[Card]] = {
    scala.io.StdIn.readLine("your-move[enter pass|draw or comma-separated cards]:>") match {
      case "draw" => Left(Draw())
      case "pass" => Left(Pass())
      case cmd =>
        val parsedCards: Option[List[Card]] = cards"${cmd}"
        parsedCards match {
          case Some(list) =>
            Right(list)
          case _ =>
            log.info("wrong cards or command, try again")
            enterMoveCards()
        }
    }
  }

}
