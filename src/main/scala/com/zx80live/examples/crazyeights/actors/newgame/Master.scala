package com.zx80live.examples.crazyeights.actors.newgame

import java.util.concurrent.TimeUnit

import akka.actor.{ActorLogging, ActorRef, Props, UntypedActor}
import com.zx80live.examples.crazyeights.actors.newgame.Messages._
import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{Crazy8Workspace, WrongDiscardEvent}
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}
import com.zx80live.examples.crazyeights.util.{CircularList, ConsoleRenderer}

import scala.concurrent.duration.Duration

/**
 *
 * @author Andrew Proshkin
 */
class Master extends UntypedActor with ActorLogging with ConsoleRenderer {

  implicit val timeout = Duration.create(2, TimeUnit.SECONDS)

  private var workspace: Workspace = new Crazy8Workspace

  private var players = new CircularList[ActorRef]()


  @scala.throws[Exception](classOf[Exception])
  override def onReceive(message: Any): Unit = message match {
    case NewGame(count) => actionNewGame(count)
    case Discard(cards) => actionDiscard(cards)
    case Draw() => actionDraw()
    case EmptyCards() => actionEmptyCards()
    case Pass() => actionPass()
    case SetSuit(suit) => actionSetSuit(suit)
    case Exit() => actionExit()

    case _ => actionUnsupportedMessage(message)
  }

  def actionSetSuit(suit: Option[Suit.Value]) = {
    workspace.currentCard match {
      case Card(Rank.Eight, _) if suit.isDefined =>
        log.info(s"set suit($suit) from ${sender().path.name}")
        workspace.setCurrentSuit(suit.get)
      case _ =>
        log.error(s"can't change suit($suit) because current card is not eight")
    }
    sender ! NextMove(workspace.currentCard)
  }

  def actionNewGame(count: Int) = {
    log.info(s"create new workspace with $count players")
    workspace = new Crazy8Workspace
    log.info(workspace.toString)
    log.info(s"deals cards for players..")
    workspace.deal(count) match {
      case Right(list) =>
        log.info(s"create players cards for 1 human and ${list.length - 1} AI:")
        log.info("workspace state:")
        log.info(workspace.toString)

        players = new CircularList[ActorRef]()

        list.tail foreach { playersCards =>
          val player = context.actorOf(Props[Player], s"player-${players.length}")
          players.insert(player)
          player ! Deal(playersCards)
        }

        val human = context.actorOf(Props[HumanConsolePlayer], s"player-human")
        players.insert(human)
        human ! Deal(list.head)

        players.next ! NextMove(workspace.currentCard)

      case Left(e) =>
        log.error(e.toString)
        actionExit()
    }
  }

  def actionDiscard(cards: List[Card]) = {
    workspace.discardCards(cards) match {
      case Left(e) =>
        val evt = new WrongDiscardEvent
        log.error(s"$evt(${toString(cards)}) from ${sender().path.name}")
        sender ! DiscardResult(workspace.currentCard, cards, evt)
      case Right(evt) =>
        log.info(s"$evt(${toString(cards)}) from ${sender().path.name}")
        sender ! DiscardResult(workspace.currentCard, cards, evt)
      //log.info("switch to the next player")
      //actionPass()
    }
  }

  def actionDraw() = {
    workspace.drawCard() match {
      case Some(card) =>
        log.info(s"draw $card to ${sender().path.name}")
        sender ! DrawResult(Some(card), workspace.currentCard)
      case _ =>
        log.error(s"can't draw card, ws = $workspace")
        actionExit()
    }
  }

  def actionEmptyCards() = {
    log.info(s"player ${sender()} win")
    actionExit()
  }

  def actionPass() = {
    val nextPlayer = players.next
    log.info(s"accept pass from ${sender().path.name}, move to ${nextPlayer.path.name}")
    nextPlayer ! NextMove(workspace.currentCard)
  }

  def actionExit() = {
    log.info(s"Bye!")
    getContext().system.deadLetters

    System.exit(0)
  }

  def actionUnsupportedMessage(m: Any) = log.warning(s"accept unsupported: $m")

}
