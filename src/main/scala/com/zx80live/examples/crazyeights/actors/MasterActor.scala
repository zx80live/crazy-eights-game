package com.zx80live.examples.crazyeights.actors

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.actors.infrastructure.ConsoleActor
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}

import scala.concurrent.duration.Duration

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with Crazy8MovePatterns with ActorLogging {

  implicit val timeout = Duration.create(2, TimeUnit.SECONDS)

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
          list.tail foreach { playersCards =>
            val player = context.actorOf(Props[AIPlayerActor])
            players = player :: players

            player ! Deal(playersCards)
          }


          val human = context.actorOf(Props[ConsoleActor])
          players = human :: players
          human ! "GetCmd"

        case Left(e) => log.error(e.toString)
      }

    case text: String => log.info(s"accept string: $text")
    case e@_ => log.error(s"accept unsupported message: $e")
  }
}
