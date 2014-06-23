package com.zx80live.examples.crazyeights.actors

import java.util.concurrent.TimeUnit

import akka.actor._
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.actors.infrastructure.ConsoleActor
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.Rank.Eight
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}

import scala.concurrent.duration.Duration

/**
 * Temporary prototype of Master actor
 *
 * TODO refactoring dirty code and mutable states
 *
 * @author Andrew Proshkin
 */
class MasterActor extends UntypedActor with Crazy8MovePatterns with ActorLogging {

  implicit val timeout = Duration.create(2, TimeUnit.SECONDS)

  private var workspace: Workspace = new Crazy8Workspace
  private var players: List[ActorRef] = Nil

  private var currentPlayerIndex: Option[Int] = None


  @scala.throws[Exception](classOf[Exception])
  override def onReceive(message: Any): Unit = {
    message match {
      case SetSuit(suit) =>
        workspace.currentCard match {
          case Card(Eight, _) =>
            log.info(s"do change suit to $suit")
            workspace.setCurrentSuit(suit) //TODO match result
          case _ =>
            log.error(s"can't change $suit because current card is not eight")
        }
        sender ! NextMove(workspace)
      case Exit() =>
        log.info(s"Bye!")
        getContext().system.deadLetters
        //TODO refactoring (find safe shutdown feature)
        System.exit(0)

      case Discard(list) =>
        log.info(s"accept discard $list")
        workspace.discardCards(list) match {
          case Left(e) =>
            log.error(s"accept wrong discard: $list")
            sender ! WrongDiscard(list, workspace, e.getMessage)
          case Right(evt) =>
            log.info(s"accept $evt")
            sender ! SuccessDiscard(list, workspace, evt)
        }

      case Pass(None) =>
        log.info(s"accept pass user${sender.path}")


      case Draw() =>
        log.info(s"accept draw")

        workspace.drawCard() match {
          case Some(card) =>
            log.info(s"draw from ws $card")
            sender ! DrawedCard(card, workspace)
          case _ =>
            log.error(s"stockpile is empty, ws = $workspace")
            sender ! "can't draw card, stockpile is empty, ws = $workspace"
        }

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
              val player = context.actorOf(Props[AIPlayerActor], s"player-${players.length}")
              players = player :: players

              player ! Deal(playersCards)
            }


            val human = context.actorOf(Props[ConsoleActor], s"player-human")
            players = human :: players
            human ! DealAndNextMove(list.head, workspace)

          case Left(e) => log.error(e.toString)
        }

      case Some(text) => log.info(s"accept some string: $text")
      case text: String => log.info(s"accept string: $text")
      case e@_ => log.error(s"accept unsupported message: $e")
    }
  }


}
