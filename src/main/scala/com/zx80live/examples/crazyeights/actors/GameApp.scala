package com.zx80live.examples.crazyeights.actors

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.Messages.NewGame

/**
 *
 * @author Andrew Proshkin
 */
object GameApp extends App {
  val system = ActorSystem("GameActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")

  master ! "Hello"
  master ! NewGame(2)
}
