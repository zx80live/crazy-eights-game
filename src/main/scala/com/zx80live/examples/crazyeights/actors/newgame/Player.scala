package com.zx80live.examples.crazyeights.actors.newgame

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.actors.newgame.Messages.{DiscardResult, DrawResult, NextMove}
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8._
import com.zx80live.examples.crazyeights.util.PrettyListView

/**
 *
 * @author Andrew Proshkin
 */
class Player extends Actor with ActorLogging with PrettyListView with Crazy8MovePatterns {
  var cards: List[Card] = Nil

  override def receive: Receive = {
    case Deal(xs: List[Card]) => actionDeal(xs)
    case NextMove(c) => actionNextMove(c)
    case DrawResult(draw, current, msg) => actionDrawResult(draw, current, msg)
    case DiscardResult(c: Card, xs: List[Card], evt: DiscardEvent) => actionDiscardResult(c, xs, evt)

    case m@_ => actionUnsupportedMessage(m)
  }

  def actionDeal(xs: List[Card]) = {
    log.info(s"deal(${prettyList(xs)})")
    cards = xs
  }

  def actionNextMove(c: Card) = {
    if (cards.length != 0) {
      findPreferred(c, cards) match {
        case xs if xs.length > 0 =>
          log.info(s"current($c), discard(${prettyList(xs)})")
          sender ! Discard(xs)
        case _ =>
          log.info(s"current($c), draw request")
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
    log.info(s"$evt for discard(${prettyList(xs)})")
    evt match {
      case f: WrongDiscardEvent => actionNextMove(current)
      case _ => actionPass()
    }
  }

  def actionUnsupportedMessage(m: Any) = log.warning(s"unsupported message: $m")

  def actionPass() = {
    log.info("pass request")
    sender ! Pass()
  }
}

