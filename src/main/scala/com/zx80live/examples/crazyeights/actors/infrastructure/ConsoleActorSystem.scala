package com.zx80live.examples.crazyeights.actors.infrastructure

import java.util.concurrent.TimeUnit

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.zx80live.examples.crazyeights.actors.MasterActor
import com.zx80live.examples.crazyeights.actors.Messages.{NewGame, WorkspaceStatus}
import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


/**
 *
 * @author Andrew Proshkin
 */
object ConsoleActorSystem extends App with GameHelp {

  val system = ActorSystem("GameActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")

  var cards: List[Card] = Nil

  implicit private val timeout: Timeout = Duration.create(2, TimeUnit.SECONDS)

  var ok = true
  val defaultPrompt = "cmd:>"
  var prompt = defaultPrompt

  while (ok) {
    scala.io.StdIn.readLine(prompt) match {
      case r"new (\d+)$count" =>
        val playersCount: Option[Int] = count

        val future: Future[Any] = playersCount match {
          case Some(value: Int) =>
            master ? NewGame(value)
          case None => master ? NewGame()
        }

        Await.result(future, Duration.create(2, TimeUnit.SECONDS)).asInstanceOf[Some[List[Card]]] match {
          case Some(list) =>
            cards = list
            println(s"HUMAN DEAL CARDS $list")
          case _ =>
            //TODO refactoring
            println("UNKNOWN ERROR")
        }



      //val future: Future[Any] = master ? ""
      //        private val future: Future[Any] = master ? CreateHuman()
      //        val result = Await.result(future, Duration.create(2, TimeUnit.SECONDS)).asInstanceOf[Some[String]]
      //        println(s"result = $result")




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
