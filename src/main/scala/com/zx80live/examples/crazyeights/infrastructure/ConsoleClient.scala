package com.zx80live.examples.crazyeights.infrastructure

/**
 *
 * @author Andrew Proshkin
 */
object ConsoleClient extends App with GameHelp {
  //  val system = ActorSystem("GameActorSystem")
  //  val master = system.actorOf(Props[MasterActor], name = "master")
  //
  //  master ! "Hello"
  //  master ! NewGame(2)
  //  master ! WorkspaceStatus()

  var ok = true
  while (ok) {
    scala.io.StdIn.readLine("cmd:>") match {
      case "about" =>
        printAbout()

      case "help" | "h" | "?" =>
        printHelp()

      case "terms" | "t" =>
        printTerms()

      case "rules" | "r" =>
        printRules()

      case "exit" | "e" | "quit" | "q" | "end" =>
        println("Bye!")
        ok = false
      case s@_ =>
        println(s"unknown command, You can use 'help', 'rules'")
    }
  }

}
