package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe
import com.zx80live.examples.crazyeights.actors.Messages.{DealAndNextMove, NextMove}
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
      scala.io.StdIn.readLine("your-move[enter comma-separated cards]:>") match {
        case yourMove =>
          val yourMoveCards: Option[List[Card]] = cards"${yourMove}"
          yourMoveCards match {
            case Some(parsedCards) =>
              log.info(s"you enter cards $parsedCards")
            case _ =>
              log.info("wrong cards, try again")
          }
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


}
