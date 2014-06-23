package com.zx80live.examples.crazyeights.infrastructure

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.MasterActor
import com.zx80live.examples.crazyeights.actors.Messages.{NewGame, WorkspaceStatus}


/**
 *
 * @author Andrew Proshkin
 */
object ConsoleClient extends App with GameHelp {

  import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._

  val system = ActorSystem("GameActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")


  //  master ! WorkspaceStatus()

  var ok = true
  while (ok) {
    scala.io.StdIn.readLine("cmd:>") match {
      case r"new (\d+)$count" =>
        master ! NewGame(2)

      case "status" | "stat" | "st" =>
        master ! WorkspaceStatus()

      case "about" =>
        printAbout()

      case "help" | "h" | "?" =>
        printHelp()

      case "terms" | "t" =>
        printTerms()

      case "rules" | "r" =>
        printRules()


      case "exit" | "e" | "quit" | "q" | "end" =>
        system.shutdown()
        println("Bye!")
        ok = false
      case s@_ =>
        println(s"unknown command, You can use 'help', 'rules'")
    }
  }

}
