package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe
import com.zx80live.examples.crazyeights.actors.Messages.{DealAndNextMove, Discard, NextMove, Pass}
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

      fetchMoveCards() match {
        case Some(moveCards) =>
          Future({
            Some(Discard(moveCards))
          }) recover {
            case t =>
              None
          } pipeTo sender

        case _ => Future({
          Some(Pass())
        }) recover {
          case t =>
            None
        } pipeTo sender

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

  private def fetchMoveCards(): Option[List[Card]] = {
    scala.io.StdIn.readLine("your-move[enter comma-separated cards or 'pass']:>") match {
      case "pass" => None
      case cmd =>
        val parsedCards: Option[List[Card]] = cards"${cmd}"
        parsedCards match {
          case Some(list) =>
            Some(list)
          case _ =>
            log.info("wrong cards, try again")
            fetchMoveCards()
        }
    }
  }

}
