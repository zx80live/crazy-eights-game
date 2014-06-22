package com.zx80live.examples.crazyeights.cards



import scala.util.Random

/**
 * Helper contains sets for standard ranks, suits and 54-cards decks.
 *
 * @author Andrew Proshkin
 */
trait CardsHelper {
  import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._

  val rankStrings: List[String] = "2" :: "3" :: "4" :: "5" :: "6" :: "7" :: "8" :: "9" :: "10" :: "J" :: "Q" :: "K" :: "A" :: "★" :: "☆" :: Nil
  val suitStrings: List[String] = "♠" :: "♥" :: "♦" :: "♣" :: Nil
  lazy val deck52Strings: List[String] = for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield s"$r$s"
  //lazy val deck54Strings: List[String] = "★" :: "☆" :: (for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield s"$r$s")
  lazy val deck54Strings: List[String] = "★" :: "☆" :: deck52Strings


  lazy val ranks: List[Rank.Value] = Rank.values.toList
  lazy val suits: List[Suit.Value] = Suit.values.toList
  lazy val deck52: List[Card] = for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield card"$r$s".get
  //lazy val deck54: List[Card] = card"★".get :: card"☆".get :: (for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield card"$r$s".get)
  lazy val deck54: List[Card] = card"★".get :: card"☆".get :: deck52

  def shuffle(deck: List[Card]): List[Card] = Random.shuffle(deck)
}
