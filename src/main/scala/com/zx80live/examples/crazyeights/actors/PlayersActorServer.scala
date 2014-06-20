package com.zx80live.examples.crazyeights.actors

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import com.zx80live.examples.crazyeights.actors.messages.{Say, NewGame}
import com.zx80live.examples.crazyeights.model.{Suit, Rank, Card}

/**
 *
 * @author Andrew Proshkin
 */
object PlayersActorServer extends App {

  var system: Option[ActorSystem] = None

  def run(): Unit = {
    val system = ActorSystem("MySystem")
    val player1 = system.actorOf(Props[PlayerActor], name = "player1")
    val player2 = system.actorOf(Props[PlayerActor], name = "player2")
    player1 ! NewGame()
    player1 ! Say("Hello, all")
    player1 ! Say("how are you?")
    player1 ! Card(Rank.Ace, Suit.Clubs)
  }

  run()
}
