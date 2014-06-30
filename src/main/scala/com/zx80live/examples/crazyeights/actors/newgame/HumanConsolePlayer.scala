package com.zx80live.examples.crazyeights.actors.newgame

import com.zx80live.examples.crazyeights.actors.GameHelp
import com.zx80live.examples.crazyeights.actors.newgame.Messages.{Discard, Pass, Draw}
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.CardsDSL._

/**
 *
 * @author Andrew Proshkin
 */
class HumanConsolePlayer extends Player with GameHelp {

  override def actionNextMove(c: Card): Unit = {
    log.info(s"your cards: ${prettyList(cards)}, current($c)")
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
            log.info(s"suggest move:  ${prettyList(preferred)}")
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
}
