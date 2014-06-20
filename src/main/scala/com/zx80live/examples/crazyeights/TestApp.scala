package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.model.Rank._
import com.zx80live.examples.crazyeights.model.Suit._
import com.zx80live.examples.crazyeights.model.{Card, Rank, Suit}

import scala.util.matching.Regex

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

  import scala.language.implicitConversions

  implicit class RegexContext(sc: StringContext) {
    def r = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)
  }

  implicit def string2Rank(str: String): Option[Rank.Value] = str.trim match {
    case "2" => Some(Two)
    case "3" => Some(Three)
    case "4" => Some(Four)
    case "5" => Some(Five)
    case "6" => Some(Six)
    case "7" => Some(Seven)
    case "8" => Some(Eight)
    case "9" => Some(Nine)
    case "10" => Some(Ten)
    case "J" => Some(Jack)
    case "Q" => Some(Queen)
    case "K" => Some(King)
    case "A" => Some(Ace)
    case "\u2605" | "BJ" => Some(BlackJoker)
    case "\u2606" | "WJ" => Some(WhiteJoker)
    case _ => None
  }

  implicit def string2Suit(str: String): Option[Suit.Value] = str.trim match {
    case "\u2660" | "S" => Some(Spades)
    case "\u2665" | "H" => Some(Hearts)
    case "\u2666" | "D" => Some(Diamonds)
    case "\u2663" | "C" => Some(Clubs)
    case _ => None
  }

  /**
   * 2♦,
   * ☆
   *
   * @param str - 
   * @return
   */
  implicit def string2Card(str: String): Option[Card] = str match {
    case r"\s*(\u2605|\u2606|WJ|BJ)$rank\s*" =>
      (rank: Option[Rank.Value]) match {
        case Some(rv) => Some(Card(rv))
        case (None) => None
      }
    case r"\s*(\d+|\w)$rank\s*(\w|\u2660|\u2665|\u2666|\u2663)$suit\s*" =>
      (rank: Option[Rank.Value], suit: Option[Suit.Value]) match {
        case (None, _) | (_, None) | (None, None) => None
        case (Some(rv), Some(sv)) => Some(Card(rv, sv))
      }

    case _ => None
  }

  val r: Option[Rank.Value] = "WJ"
  println(r)

  val c: Option[Card] = "2D"
  val d: Option[Card] = "BJ"
  println(c)
  println(d)

  //  val a: Option[Rank.Value] = " A "
  //  val b: Option[Suit.Value] = " D "
  //  println(a + " " + b)


  implicit def string2Cards(str: String): List[Card] = ???

  lazy val deck54: List[Card] = createDeck54
  //  deck54 foreach println
  //  println("shuffle:")
  //  Random.shuffle(deck54) foreach println

  //val cards = List(Card(Eight, Diamonds), Card(Eight, Spades), Card(Two, Diamonds), Card(Three, Diamonds), Card(Two, Spades), Card(Ace, Clubs), Card(Two, Clubs), Card(Ace, Diamonds))

  //GameLogic.findEight(cards)

  //cards.filter(_.rank == Two) foreach println
}
