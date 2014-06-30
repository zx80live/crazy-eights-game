package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
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
          log.info(s"(${toString(cards)}) [$c]: discard(${toString(xs)})")
          sender ! Discard(xs)
        case _ =>
          log.info(s"(${toString(cards)}) [$c]: draw")
          sender ! Draw()
      }
    } else {
      sender ! EmptyCards()
    }
  }

  def actionDrawResult(draw: Option[Card], current: Card, msg: String) = {
    log.info(s"accept draw($draw), current($current) $msg")
    draw match {
      case Some(c) =>
        cards = c :: cards
        actionNextMove(current)
      case None =>
        actionPass()
    }

  }

  def actionDiscardResult(current: Card, xs: List[Card], evt: DiscardEvent) = {
    log.info(s"$evt(${toString(xs)})")
    evt match {
      case f: WrongDiscardEvent => actionNextMove(current)
      case s: EightDiscardEvent =>
        diffCards(xs)
        actionSetSuit(current, xs)
        actionPass()
      case _ => diffCards(xs); actionPass()
    }
  }

  def actionSetSuit(current: Card, xs: List[Card]) = {
    log.info(s"keep current suit [$current]")
  }

  def actionUnsupportedMessage(m: Any) = log.warning(s"unsupported($m)")

  def actionPass() = {
    log.info("pass")
    sender ! Pass()
  }

  def actionExit() {
    log.info("exit")
    sender ! Exit()
  }

  def diffCards(xs: List[Card]) = {
    cards = cards diff xs
  }
}

