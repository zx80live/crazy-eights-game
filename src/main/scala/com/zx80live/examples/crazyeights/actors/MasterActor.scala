package com.zx80live.examples.crazyeights.actors

import java.util.concurrent.TimeUnit

import akka.actor._
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.{Suit, Card}
import com.zx80live.examples.crazyeights.cards.Rank.Eight
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8MovePatterns, Crazy8Workspace}
import com.zx80live.examples.crazyeights.util.PrettyListView

import scala.concurrent.duration.Duration

/**
 * Temporary prototype of Master actor
 *
 * TODO refactoring dirty code and mutable states
 *
 * @author Andrew Proshkin
 */
class MasterActor extends UntypedActor with Crazy8MovePatterns with ActorLogging with PrettyListView {

  implicit val timeout = Duration.create(2, TimeUnit.SECONDS)

  private var workspace: Workspace = new Crazy8Workspace
  private var players: List[ActorRef] = Nil

  private var playersIterator: Iterator[ActorRef] = Nil.iterator

  private def nextPlayer() = {
    if (!playersIterator.hasNext)
      playersIterator = players.iterator

    playersIterator.next()
  }

  @scala.throws[Exception](classOf[Exception])
  override def onReceive(message: Any): Unit = {

    message match {

      case NewGame(playersCount) => actionNewGame(playersCount)

      case Discard(list) => actionDiscard(list)

      case Draw() => actionDraw()

      case SetSuit(suit) => actionSetSuit(suit)

      case Exit() => exitGame()


      case Pass(None) =>
        log.info(s"accept pass user${sender.path}")
        if (playersIterator.hasNext) {
          val nextPlayer = playersIterator.next()
          nextPlayer ! NextMove(workspace)
          log.info(s"move to next $nextPlayer")
        } else {
          playersIterator = players.iterator
          val nextPlayer = playersIterator.next()
          nextPlayer ! NextMove(workspace)
          log.info(s"move to first $nextPlayer")
        }

      case Pass(Some("WithJoker")) =>
        log.info(s"accept pass user${sender.path}")
        if (playersIterator.hasNext) {
          val nextPlayer = playersIterator.next()
          nextPlayer ! NextMove(workspace, canAnyCardMove = true)
          log.info(s"move to next $nextPlayer")
        } else {
          playersIterator = players.iterator
          val nextPlayer = playersIterator.next()
          nextPlayer ! NextMove(workspace, canAnyCardMove = true)
          log.info(s"move to first $nextPlayer")
        }

      case WorkspaceStatus() => actionWorkspaceStatus()

      case Win() => actionWin()

      case Some(text) => log.info(s"accept some string: $text from $sender")
      case text: String => log.info(s"accept string: $text from $sender")
      case e@_ => log.error(s"accept unsupported message: $e from $sender")
    }
  }

  private def actionSetSuit(suit: Suit.Value) = {
    workspace.currentCard match {
      case Card(Eight, _) =>
        log.info(s"do change suit to $suit")
        workspace.setCurrentSuit(suit) //TODO match result
      case _ =>
        log.error(s"can't change $suit because current card is not eight")
    }
    sender ! NextMove(workspace)
  }

  private def actionDiscard(list: List[Card]) = {
    log.info(s"accept discard ${prettyList(list)} from ${sender.path}")
    workspace.discardCards(list) match {
      case Left(e) =>
        log.error(s"accept wrong discard ${prettyList(list)} from ${sender.path}")
        sender ! WrongDiscard(list, workspace, e.getMessage)
      case Right(evt) =>
        log.info(s"accept $evt")
        sender ! SuccessDiscard(list, workspace, evt)
    }
  }

  private def actionDraw() = {
    log.info(s"accept draw")

    workspace.drawCard() match {
      case Some(card) =>
        log.info(s"draw from ws $card")
        sender ! DrawedCard(card, workspace)
      case _ =>
        log.error(s"stockpile is empty, ws = $workspace")
        sender ! "can't draw card, stockpile is empty, ws = $workspace"
    }
  }

  private def actionWin() = {
    log.info(s"PLAYER $sender WIN!")
    exitGame()
  }

  private def actionWorkspaceStatus() = {
    log.info(workspace.toString)
  }

  private def actionNewGame(playersCount: Int) = {
    log.info(s"create new workspace with $playersCount players")
    workspace = new Crazy8Workspace
    log.info(workspace.toString)
    log.info(s"deals cards for players..")
    workspace.deal(playersCount) match {
      case Right(list) =>
        log.info(s"create players cards for 1 human and ${list.length - 1} AI:")
        log.info("workspace state:")
        log.info(workspace.toString)

        players = Nil
        list.tail foreach { playersCards =>
          val player = context.actorOf(Props[AIPlayerActor], s"player-${players.length}")
          players = player :: players

          player ! Deal(playersCards, workspace)
        }


        val human = context.actorOf(Props[ConsoleActor], s"player-human")
        players = human :: players
        playersIterator = players.iterator
        playersIterator.next
        human ! DealAndNextMove(list.head, workspace)

      case Left(e) =>
        log.error(e.toString)
        exitGame()
    }
  }


  //TODO refactoring (find safe shutdown feature)
  private def exitGame() {
    log.info(s"Bye!")
    getContext().system.deadLetters

    System.exit(0)
  }
}
