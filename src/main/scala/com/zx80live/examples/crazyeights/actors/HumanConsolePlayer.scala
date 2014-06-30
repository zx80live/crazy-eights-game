package com.zx80live.examples.crazyeights.actors

import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.CardsDSL._
import com.zx80live.examples.crazyeights.cards.{Card, Suit}

/**
 *
 * @author Andrew Proshkin
 */
class HumanConsolePlayer extends Player with GameHelp {

  override def actionNextMove(c: Card): Unit = {
    log.info(s"your cards: ${toString(cards)}, current($c)")
    log.info("enter pass|p|draw|d|exit|e|suggest|sg or comma-separated cards:>")

    scala.io.StdIn.readLine() match {

      case "help" | "h" | "?" =>
        printHelp()
        actionNextMove(c)

      case "about" =>
        printAbout()
        actionNextMove(c)

      case "rules" | "r" =>
        printRules()
        actionNextMove(c)

      case "draw" | "d" =>
        log.info("request draw card")
        sender ! Draw()

      case "suggest" | "sg" =>
        findPreferred(c, cards) match {
          case preferred if preferred.length > 0 =>
            log.info(s"suggest move:  ${toString(preferred)}")
          case _ =>
            log.info("suggest move:  None, use commands draw|pass")
        }
        actionNextMove(c)

      case "pass" | "p" =>
        log.warning("pass move")
        sender ! Pass()

      case "exit" | "e" | "q" | "quit" =>
        actionExit()

      case xs@_ =>
        val parsedCards: Option[List[Card]] = cards"$xs"
        parsedCards match {
          case Some(list) =>
            sender ! Discard(list)
          case _ =>
            log.info("*** ERROR: wrong cards or command, try again *** ")
            actionNextMove(c)
        }
    }
  }


  override def actionSetSuit(current: Card, xs: List[Card]) = {
    log.info("\nenter new suit [♠, ♥, ♦, ♣] or empty string to keep current:>")
    val suit: Option[Suit.Value] = scala.io.StdIn.readLine() match {
      case str if str.length > 0 =>
        suit"$str": Option[Suit.Value]
      case _ => None
    }
    sender ! SetSuit(suit)
  }
}
