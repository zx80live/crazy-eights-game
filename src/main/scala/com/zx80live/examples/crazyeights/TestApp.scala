package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.model.Rank._
import com.zx80live.examples.crazyeights.model.Suit._
import com.zx80live.examples.crazyeights.model.{Card, Rank, Suit}

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

  /**
   * 2♦,
   * ☆
   *
   * @param str - 
   * @return
   */
  implicit def string2Card(str: String): Card = ???

  implicit def string2Cards(str: String): List[Card] = ???

  lazy val deck54: List[Card] = createDeck54
  //  deck54 foreach println
  //  println("shuffle:")
  //  Random.shuffle(deck54) foreach println

  val cards = List(Card(Eight, Diamonds), Card(Eight, Spades), Card(Two, Diamonds), Card(Three, Diamonds), Card(Two, Spades), Card(Ace, Clubs), Card(Two, Clubs), Card(Ace, Diamonds))

  //GameLogic.findEight(cards)

  cards.filter(_.rank == Two) foreach println
}
