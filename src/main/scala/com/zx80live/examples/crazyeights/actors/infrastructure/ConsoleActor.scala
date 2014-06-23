package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{Actor, ActorLogging}
import akka.pattern.pipe

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 *
 * @author Andrew Proshkin
 */
class ConsoleActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case "GetCmd" =>
      scala.io.StdIn.readLine("human-command:>") match {
        case cmd@_ =>
          val f = Future({
            Some(cmd)
          }) recover {
            case t =>
              log.error(s"can't get command $t")
              None
          }
          f pipeTo sender
      }

    case m@_ => log.error(s"unsupported message $m")
  }
}
