package com.zx80live.examples.crazyeights.actors

import akka.actor.{ActorLogging, Actor}
import com.zx80live.examples.crazyeights.actors.messages.{NewGame, Say}
import com.zx80live.examples.crazyeights.model.Card

/**
 *
 * @author Andrew Proshkin
 */
class PlayerActor extends Actor with ActorLogging {
  var cards = scala.collection.mutable.ListBuffer[Card]()

  override def receive = {
    case m: NewGame =>
      cards.clear()
      println("new game")
    case c: Card =>
      cards += c
      println(s"add card $c")
    case Say(msg) => println(msg)
    case _ => println("I don't know")
  }
}
