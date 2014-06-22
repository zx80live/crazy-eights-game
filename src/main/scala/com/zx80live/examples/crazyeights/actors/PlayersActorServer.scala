package com.zx80live.examples.crazyeights.actors

import akka.actor.{ActorSystem, Props}
import com.zx80live.examples.crazyeights.actors.messages.{NewGame, Say}
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}

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
