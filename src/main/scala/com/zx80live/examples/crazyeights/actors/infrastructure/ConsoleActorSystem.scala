package com.zx80live.examples.crazyeights.actors.infrastructure

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.MasterActor
import com.zx80live.examples.crazyeights.actors.Messages.NewGame


/**
 *
 * @author Andrew Proshkin
 */
object ConsoleActorSystem extends App with GameHelp {

  val system = ActorSystem("ConsoleActorSystem")
  val master = system.actorOf(Props[MasterActor], name = "master")

  master ! NewGame(2)

}
