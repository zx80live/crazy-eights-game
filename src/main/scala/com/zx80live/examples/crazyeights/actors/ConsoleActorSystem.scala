package com.zx80live.examples.crazyeights.actors

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.Messages.NewGame
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._


/**
 *
 * @author Andrew Proshkin
 */
object ConsoleActorSystem extends App with GameHelp {

  val system = ActorSystem("ConsoleActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")

  //master ! NewGame(2)
  var ok = true
  while (ok) {
    println("\nenter command or help|h or new [playersCount]:>")
    scala.io.StdIn.readLine() match {
      //TODO some IDE (for example Idea) doesn't support syntax for regex interpolated string but compile it
      case r"\s*new\s*(\d+)$c\s*" =>
        println(s"create new game with [$c] players")
        val playersCount: Option[Int] = s"$c"
        master ! NewGame(playersCount.get)
        ok = false
      case "help" | "h" | "?" =>
        printHelp()
      case "about" =>
        printAbout()
      case "rules" | "r" =>
        printRules()
      case "exit" | "quit" | "q" | "e" =>
        println("Bye!")
        system.shutdown()
        System.exit(0)
      case _ =>
        println("unknown command, enter help or new [playersCount]")
    }
  }


}
