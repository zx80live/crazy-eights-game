package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.model.{Rank, Suit, Card}

import scala.util.Random

/**
 *
 * @author Andrew Proshkin
 */
object TestApp extends App {

  def createDeck54: List[Card] = {
    val ranks = Rank.values.toList.filterNot(r => r.eq(Rank.BlackJoker) || r.eq(Rank.WhiteJoker))
    val suits = Suit.values.toList.filterNot(_.eq(Suit.Special))
    val deck52 = for (r <- ranks; s <- suits) yield Card(r, s)

    Card(Rank.BlackJoker, Suit.Special) :: Card(Rank.WhiteJoker, Suit.Special) :: deck52
  }


  val deck54: List[Card] = createDeck54
  deck54 foreach println
  println("shuffle:")
  Random.shuffle(deck54) foreach println
}
