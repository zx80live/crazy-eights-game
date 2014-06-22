package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}


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

  import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._

  import scala.language.implicitConversions


  val r: Option[Rank.Value] = "WJ"
  //println(r)

  val c: Option[Card] = "2D"
  val d: Option[Card] = "BJ"
  //println(c)
  //println(d)

  //println(List[Option[Card]]("2D", "3S", "BJ", "UNKNOWN", "WJ").flatten)
  //  println("2D , 3 ♣, 3S, BJ, UNKNOWN, 2♦  , WJ": Option[List[Card]])
  //  println("ABCD,A": Option[List[Card]])

  //println("2D,3D,4S": Option[List[Card]])

  //  val a: Option[Rank.Value] = " A "
  //  val b: Option[Suit.Value] = " D "
  //  println(a + " " + b)


  lazy val deck54: List[Card] = createDeck54
  //  deck54 foreach println
  //  println("shuffle:")
  //  Random.shuffle(deck54) foreach println

  //val cards = List(Card(Eight, Diamonds), Card(Eight, Spades), Card(Two, Diamonds), Card(Three, Diamonds), Card(Two, Spades), Card(Ace, Clubs), Card(Two, Clubs), Card(Ace, Diamonds))

  //GameLogic.findEight(cards)

  //cards.filter(_.rank == Two) foreach println

  //private val either: Either[IllegalArgumentException, Card] = card"A♣"
  //println(either)
}
