package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages.NewGame
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with Crazy8MovePatterns with ActorLogging {

  private var workspace: Workspace = new Crazy8Workspace


  override def receive: Receive = {
    case NewGame(playersCount) =>
      log.info(s"accept NewGame($playersCount)")
      workspace = new Crazy8Workspace
      log.info("create new workspace:")
      log.info(workspace.toString)
      log.info(s"prepare deals cards for playersCount = $playersCount")
      workspace.deal(playersCount) match {
        case Right(list) =>
          log.info(s"create players cards for 1 human and ${list.length - 1} AI:")
          list foreach { playersCards =>
            log.info(playersCards.toString())
          }
          log.info("workspace state:")
          log.info(workspace.toString)

        case Left(e) => log.error(e.toString)
      }

    case text: String => log.info(s"accept string: $text")
    case e@_ => log.error(s"accept unsupported message: $e")
  }
}
