package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages.{Discard, NewGame}
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with Crazy8MovePatterns with ActorLogging {

  private var workspace: Workspace = new Crazy8Workspace


  override def receive: Receive = {
    case m: NewGame =>
      workspace = new Crazy8Workspace
      log.info("create new workspace:")
      log.info(workspace.toString)

    case text: String => st(s"accept $text")
    case Discard(cards) =>
      st(s"accept Discard($cards)")

  }

  def st(msg: Any) {
    log.info(msg.toString)
  }
}
