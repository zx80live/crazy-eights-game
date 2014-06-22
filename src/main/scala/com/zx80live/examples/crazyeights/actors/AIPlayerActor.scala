package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8MovePatterns

/**
 * AI player actor
 *
 * @author Andrew Proshkin
 */
class AIPlayerActor extends Actor with Player with Crazy8MovePatterns with ActorLogging {
  var cards: List[Card] = Nil

  override def receive = {
    case text: String =>
      st(s"accept Message($text)")

    case NewGame(newCards) =>
      st(s"accept NewGame($newCards)")

    case Pass(msg) =>
      st(s"accept Pass($msg)")

    case NextMove(currentCard) =>
      st(s"accept NextMove($currentCard)")

    case m@_ => st(s"accept Unknown($m)")
  }

  def st(msg: Any) = {
    log.info(msg.toString)
  }
}
