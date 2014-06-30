package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.OldMessages._
import com.zx80live.examples.crazyeights.cards.{Card, Suit, CardsDSL}
import CardsDSL._
import com.zx80live.examples.crazyeights.cards.rules.ReadonlyWorkspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8._
import com.zx80live.examples.crazyeights.util.ConsoleRenderer

/**
 *
 * @author Andrew Proshkin
 */
@Deprecated
class ConsoleActor extends Actor with ActorLogging with Crazy8MovePatterns with Crazy8DiscardsValidator with GameHelp with ConsoleRenderer {
  var cards: List[Card] = Nil
  var workspace: Option[ReadonlyWorkspace] = None

  private def getNewSuit(): Option[Suit.Value] = {
    log.info("\nenter new suit [♠, ♥, ♦, ♣] or empty string to keep current:>")
    scala.io.StdIn.readLine() match {
      case str if str.length > 0 =>
        suit"$str": Option[Suit.Value]
      case _ => None
    }
  }

  override def receive: Receive = {

    case Deal(list, ws) =>
      cards = list
      workspace = Some(ws)
      log.info(s"accept deal cards: ${toString(list)}")

    case SuccessDiscard(correctDiscardCards, ws, event) =>
      cards = cards diff correctDiscardCards
      workspace = Some(ws)
      log.info(s"discard event: $event")
      event match {
        case _: SuccessDiscardEvent =>
          enterCommand()

        case _: EightDiscardEvent =>
          getNewSuit() match {
            //TODO check integrity, synchronized with master
            case Some(suit) =>
              sender ! SetSuit(suit)
            case _ =>
              log.info("keep current suit")
              enterCommand()
          }

        case _: JokerDiscardEvent => None
          log.info("pass move because there was joker")
          sender ! Pass(Some("WithJoker"))

        case _ => None
      }


    case WrongDiscard(wrongMoveCards, ws, msg) =>
      workspace = Some(ws)
      log.error(s"wrong move $wrongMoveCards because $msg")
      enterCommand()

    case DrawedCard(card, ws) =>
      cards = card :: cards
      workspace = Some(ws)
      enterCommand()

    case DealAndNextMove(list, ws) =>
      cards = list
      workspace = Some(ws)
      enterCommand()

    case NextMove(ws, true) =>
      log.info("you can move any card because there is joker")
      workspace = Some(ws)
      enterCommand()

    case NextMove(ws, false) =>
      workspace = Some(ws)
      enterCommand()


    case m@_ => log.error(s"unsupported message $m")
  }

  private def printStatus(postProcessedText: String = ""): Unit = {
    workspace match {
      case Some(ws) =>
        log.info(
          s"""
            |
            |  Workspace(shuffle: ${ws.isShuffle}) {
            |    stockpile:   ${toString(ws.stockPile)}
            |    discardPile: ${toString(ws.discardPile)}
            |    currentCard: ${ws.currentCard.toString.trim}
            |  }
            |  your cards:    ${toString(cards)}
            |  $postProcessedText
          """.stripMargin)

      case None =>
        log.info(s"Workspace: None")
    }
  }


  private def enterCommand(postProcessedText: String = ""): Unit = {
    printStatus(postProcessedText)

    if (checkWin()) {
      //TODO synchronized check Win with MasterActor state
      log.info("*** Congratulation! You win! ***")
      sender ! Win()
    }
    else {

      log.info("enter pass|p|draw|d|exit|e|suggest|sg or comma-separated cards:>")
      scala.io.StdIn.readLine() match {


        case "help" | "h" | "?" =>
          printHelp()
        case "about" =>
          printAbout()
        case "rules" | "r" =>
          printRules()

        case "draw" | "d" =>
          log.info("request draw card")
          sender ! Draw()

        case "suggest" | "sg" =>
          val txt = findPreferred(workspace.get.currentCard, cards) match {
            case preferred if preferred.length > 0 =>
              s"suggest move:  ${toString(preferred)}"
            case _ =>
              "suggest move:  None, use commands draw|pass"
          }
          enterCommand(txt)

        case "pass" | "p" =>
          log.warning("pass move")
          sender ! Pass()

        case "exit" | "e" | "q" | "quit" =>
          sender ! Exit()

        case xs@_ =>
          val parsedCards: Option[List[Card]] = cards"$xs"
          parsedCards match {
            case Some(list) =>
              sender ! Discard(list)
            case _ =>
              enterCommand("*** ERROR: wrong cards or command, try again *** ")
          }
      }
    }
  }

  private def checkWin(): Boolean = {
    if (cards == Nil || cards.length == 0)
      true
    else
      false
  }

}
