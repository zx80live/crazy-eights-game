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
    case Discard(list) =>
      log.info(s"accept discard $list")
      workspace.discardCards(list) match {
        case Left(e) =>
          log.error(s"accept wrong discard: $list")
        case Right(evt) =>
          log.info(s"accept $evt")
      }

    case Pass(None) =>
      log.info(s"accept pass")

    case Draw() =>
      log.info(s"accept draw")

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
          human ! DealAndNextMove(list.head, workspace)

        case Left(e) => log.error(e.toString)
      }

    case Some(text) => log.info(s"accept some string: $text")
    case text: String => log.info(s"accept string: $text")
    case e@_ => log.error(s"accept unsupported message: $e")
  }
}
