package com.zx80live.examples.crazyeights.cards

import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._

import scala.util.matching.Regex

/**
 * Simple DSL for 54-cards deck.
 * Utils for conversion string to game model implicitly.
 *
 * @author Andrew Proshkin
 */
object CardsDSL {

  import scala.language.implicitConversions

  implicit def string2Int(str: String): Option[Int] = {
    try {
      Some(str.toInt)
    } catch {
      case _: Throwable => None
    }
  }

  /**
   * TODO refactoring duplicate code
   *
   * Allow to use string interpolation into: regex, card, cards list
   *
   * @param sc - string context
   */
  implicit class CardsConversionHelper(sc: StringContext) {
    def r = new Regex(sc.parts.mkString, sc.parts.tail.map(_ => "x"): _*)

    def rank(args: Any*): Option[Rank.Value] = {
      val strings = sc.parts.iterator
      val buff = new StringBuilder(strings.next())
      while (strings.hasNext) {
        buff append args.iterator.next
        buff append strings.next
      }
      buff.toString(): Option[Rank.Value]
    }

    def suit(args: Any*): Option[Suit.Value] = {
      val strings = sc.parts.iterator
      val buff = new StringBuilder(strings.next())
      while (strings.hasNext) {
        buff append args.iterator.next
        buff append strings.next
      }
      buff.toString(): Option[Suit.Value]
    }

    def card(args: Any*): Option[Card] = {
      val strings = sc.parts.iterator
      val buff = new StringBuilder(strings.next())
      while (strings.hasNext) {
        buff append args.iterator.next
        buff append strings.next
      }
      buff.toString(): Option[Card]
    }

    def cards(args: Any*): Option[List[Card]] = {
      val strings = sc.parts.iterator
      val buff = new StringBuilder(strings.next())
      while (strings.hasNext) {
        buff append args.iterator.next
        buff append strings.next
      }
      buff.toString(): Option[List[Card]]
    }
  }

  /**
   * Converts string to card rank
   *
   * @param str - card rank string representation
   * @return
   */
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

  /**
   * Converts string to card suit
   *
   * @param str - card suit string representation
   * @return
   */
  implicit def string2Suit(str: String): Option[Suit.Value] = str.trim match {
    case "\u2660" | "S" => Some(Spades)
    case "\u2665" | "H" => Some(Hearts)
    case "\u2666" | "D" => Some(Diamonds)
    case "\u2663" | "C" => Some(Clubs)
    case _ => None
  }

  /**
   * Converts string to card
   *
   * TODO some IDE (for example Idea) doesn't support syntax for regex interpolated string but compile it
   *
   * @param str - card string representation
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

  /**
   * Converts string to cards list
   *
   * @param str - cards list string representation delimited by ","
   * @return
   */
  implicit def string2CardsList(str: String): Option[List[Card]] = {
    val xs: List[Option[Card]] = str.split("\\s*,\\s*").toList.map(c => c: Option[Card])
    (xs.flatten: List[Card]) match {
      case (List()) => None
      case (value) => Some(value)
    }
  }


}
