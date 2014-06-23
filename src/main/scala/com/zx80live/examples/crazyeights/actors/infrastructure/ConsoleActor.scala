package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.rules.ReadonlyWorkspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8._
import com.zx80live.examples.crazyeights.cards.{Card, Suit}

/**
 *
 * @author Andrew Proshkin
 */
class ConsoleActor extends Actor with ActorLogging with Crazy8MovePatterns with Crazy8DiscardsValidator {
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

    case NextMove(ws) =>
      workspace = Some(ws)
      enterCommand()

    case m@_ => log.error(s"unsupported message $m")
  }


  private def enterCommand(): Unit = {
    log.info(s"\n${workspace.toString}")
    log.info(s"\nyour cards: $cards")

    if (checkWin()) {
      //TODO synchronized check Win with MasterActor state
      log.info("*** Congratulation! You win! ***")
      sender ! Win()
    }
    else {

      log.info("\nenter pass|p|draw|d|exit|e|suggest|sg or comma-separated cards:>")
      scala.io.StdIn.readLine() match {

        case "draw" | "d" =>
          log.info("request draw card")
          sender ! Draw()

        case "suggest" | "sg" =>
          findPreferred(workspace.get.currentCard, cards) match {
            case preferred if preferred.length > 0 =>
              log.info(s"the preferred move is: $preferred")
            case _ =>
              log.info("can't find preferred move, use commands draw|pass")
          }
          enterCommand()

        case "pass" | "p" =>
          log.warning("pass move")
          sender ! Pass()

        case "exit" | "e" =>
          sender ! Exit()
          System.exit(1)

        case xs@_ =>
          val parsedCards: Option[List[Card]] = cards"$xs"
          parsedCards match {
            case Some(list) =>
              sender ! Discard(list)
            case _ =>
              log.error("wrong cards or command, try again")
              enterCommand()
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
