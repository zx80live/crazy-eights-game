package com.zx80live.examples.crazyeights.actors

import akka.actor.{Actor, ActorSystem, Props}
import com.zx80live.examples.crazyeights.cards.CardsHelper

/**
 *
 * @author Andrew Proshkin
 */
class MasterActor extends Actor with CardsHelper {

  var system: Option[ActorSystem] = None

  def run(): Unit = {
    val system = ActorSystem("MySystem")
    val player1 = system.actorOf(Props[AIPlayerActor], name = "player1")
    val player2 = system.actorOf(Props[AIPlayerActor], name = "player2")
    //    player1 ! NewGame()
    //    player1 ! Say("Hello, all")
    //    player1 ! Say("how are you?")
    //    player1 ! Card(Rank.Ace, Suit.Clubs)
  }

  run()

  override def receive: Receive = ???
}
