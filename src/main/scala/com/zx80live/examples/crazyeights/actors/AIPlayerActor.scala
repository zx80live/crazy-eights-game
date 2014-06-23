package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8MovePatterns

/**
 * AI player actor
 *
 * @author Andrew Proshkin
 */
class AIPlayerActor extends Actor with Player with Crazy8MovePatterns with ActorLogging {
  var cards: List[Card] = Nil

  override def receive = {
    case Deal(list) =>
      log.info(s"accept deal cards: $list")
      cards = list

    case NextMove(ws) =>
      log.info(s"accept next move")
      sender ! Pass()

    case text: String =>
      log.info(s"accept string: $text")

    case m@_ => log.info(s"accept unsupported message $m")
  }

}
