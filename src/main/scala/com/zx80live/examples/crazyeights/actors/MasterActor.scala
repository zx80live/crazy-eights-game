package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages.Discard
import com.zx80live.examples.crazyeights.cards.CardsHelper
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8MovePatterns

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with CardsHelper with Crazy8MovePatterns with ActorLogging {



  override def receive: Receive = {
    case text: String => st(s"accept $text")
    case Discard(cards) =>
      st(s"accept Discard($cards)")

  }

  def st(msg: Any) {
    log.info(msg.toString)
  }
}
