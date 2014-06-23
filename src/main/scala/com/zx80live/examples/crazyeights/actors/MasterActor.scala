package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.zx80live.examples.crazyeights.actors.Messages.{Deal, NewGame, WorkspaceStatus}
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with Crazy8MovePatterns with ActorLogging {

  private var workspace: Workspace = new Crazy8Workspace
  private var players: List[ActorRef] = Nil


  override def receive: Receive = {
    case WorkspaceStatus() =>
      log.info(workspace.toString)

    case NewGame(playersCount) =>
      log.info(s"accept NewGame($playersCount)")
      log.info("create new workspace")
      workspace = new Crazy8Workspace
      log.info(workspace.toString)
      log.info(s"deals cards for playersCount = $playersCount")
      workspace.deal(playersCount) match {
        case Right(list) =>
          log.info(s"create players cards for 1 human and ${list.length - 1} AI:")
          log.info("workspace state:")
          log.info(workspace.toString)

          players = Nil
          list foreach { playersCards =>
            val player = context.actorOf(Props[AIPlayerActor])
            players = player :: players

            player ! Deal(playersCards)
          }


        case Left(e) => log.error(e.toString)
      }

    case text: String => log.info(s"accept string: $text")
    case e@_ => log.error(s"accept unsupported message: $e")
  }
}
