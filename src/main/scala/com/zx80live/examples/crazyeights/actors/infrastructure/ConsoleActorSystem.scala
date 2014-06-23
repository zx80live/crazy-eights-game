package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.MasterActor
import com.zx80live.examples.crazyeights.actors.Messages.NewGame


/**
 *
 * @author Andrew Proshkin
 */
object ConsoleActorSystem extends App with GameHelp {

  val system = ActorSystem("GameActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")

  master ! NewGame(2)

  //var cards: List[Card] = Nil

  //  implicit private val timeout: Timeout = Duration.create(2, TimeUnit.SECONDS)
  //
  //  var ok = true
  //  val defaultPrompt = "cmd:>"
  //  var prompt = defaultPrompt
  //
  //  while (ok) {
  //    scala.io.StdIn.readLine(prompt) match {
  //      case r"new (\d+)$count" =>
  //        master ! NewGame(value)
  //
  //
  //      case "status" | "stat" | "st" =>
  //        master ! WorkspaceStatus()
  //
  //      case "about" =>
  //        printAbout()
  //
  //      case "help" | "h" | "?" =>
  //        printHelp()
  //
  //      case "terms" | "t" =>
  //        printTerms()
  //
  //      case "rules" | "r" =>
  //        printRules()
  //
  //
  //      case "exit" | "e" | "quit" | "q" | "end" =>
  //        system.shutdown()
  //        println("Bye!")
  //        ok = false
  //      case s@_ =>
  //        println(s"unknown command, You can use 'help', 'rules'")
  //    }
  //  }

}
