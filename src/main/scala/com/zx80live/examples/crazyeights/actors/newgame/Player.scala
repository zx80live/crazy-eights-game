package com.zx80live.examples.crazyeights.actors.newgame

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.newgame.Messages._
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8._
import com.zx80live.examples.crazyeights.util.ConsoleRenderer

/**
 *
 * @author Andrew Proshkin
 */
class Player extends Actor with ActorLogging with ConsoleRenderer with Crazy8MovePatterns {
  var cards: List[Card] = Nil

  override def receive: Receive = {
    case Deal(xs: List[Card]) => actionDeal(xs)
    case NextMove(c) => actionNextMove(c)
    case DrawResult(draw, current, msg) => actionDrawResult(draw, current, msg)
    case DiscardResult(c: Card, xs: List[Card], evt: DiscardEvent) => actionDiscardResult(c, xs, evt)

    case m@_ => actionUnsupportedMessage(m)
  }

  def actionDeal(xs: List[Card]) = {
    log.info(s"deal(${toString(xs)})")
    cards = xs
  }

  def actionNextMove(c: Card) = {
    if (cards.length != 0) {
      findPreferred(c, cards) match {
        case xs if xs.length > 0 =>
          log.info(s"discard(${toString(xs)}) --> current($c)")
          sender ! Discard(xs)
        case _ =>
          log.info(s"draw request for current($c)")
          sender ! Draw()
      }
    } else {
      sender ! EmptyCards()
    }
  }

  def actionDrawResult(draw: Option[Card], current: Card, msg: String) = {
    log.info(s"draw($draw), current($current) $msg")
    draw match {
      case Some(c) =>
        cards = c :: cards
        actionNextMove(current)
      case None =>
        actionPass()
    }

  }

  def actionDiscardResult(current: Card, xs: List[Card], evt: DiscardEvent) = {
    log.info(s"$evt for discard(${toString(xs)})")
    evt match {
      case f: WrongDiscardEvent => actionNextMove(current)
      case s: EightDiscardEvent => actionSetSuit(current, xs)
      case _ => diffCards(xs); actionPass()
    }
  }

  def actionSetSuit(current: Card, xs: List[Card]) = {
    log.warning("TODO implements set suit")
    //    log.info("\nenter new suit [♠, ♥, ♦, ♣] or empty string to keep current:>")
    //    scala.io.StdIn.readLine() match {
    //      case str if str.length > 0 =>
    //        suit"$str": Option[Suit.Value]
    //      case _ => None
    //    }
    diffCards(xs)
    actionPass()
  }

  def actionUnsupportedMessage(m: Any) = log.warning(s"unsupported message: $m")

  def actionPass() = {
    log.info("pass request")
    sender ! Pass()
  }

  def actionExit() {
    log.info("exit request")
    sender ! Exit()
  }

  def diffCards(xs: List[Card]) = {
    cards = cards diff xs
  }
}

