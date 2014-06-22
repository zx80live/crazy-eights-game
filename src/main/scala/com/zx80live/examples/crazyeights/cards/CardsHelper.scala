package com.zx80live.examples.crazyeights.cards


import com.zx80live.examples.crazyeights.cards.rules.{DefaultCondition, ShuffleCondition}

import scala.util.Random

/**
 * Helper contains sets for standard ranks, suits and 54-cards decks.
 *
 * @author Andrew Proshkin
 */
trait CardsHelper {

  import com.zx80live.examples.crazyeights.cards.Rank._
  import com.zx80live.examples.crazyeights.cards.Suit._

  val rankStrings: List[String] = "2" :: "3" :: "4" :: "5" :: "6" :: "7" :: "8" :: "9" :: "10" :: "J" :: "Q" :: "K" :: "A" :: "★" :: "☆" :: Nil
  val suitStrings: List[String] = "♠" :: "♥" :: "♦" :: "♣" :: Nil
  lazy val deck52Strings: List[String] = for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield s"$r$s"
  lazy val deck54Strings: List[String] = "★" :: "☆" :: deck52Strings
  lazy val deck52StringPairs: List[(String, String)] = for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield (r, s)
  lazy val deck54StringPairs: List[(String, String)] = ("★", "") ::("☆", "") :: deck52StringPairs

  lazy val ranks: List[Rank.Value] = Rank.values.toList
  lazy val suits: List[Suit.Value] = Suit.values.toList
  lazy val deck52: List[Card] = for (r <- ranks.filter(r => r != BlackJoker && r != WhiteJoker); s <- suits.filter(_ != Special)) yield Card(r, s)
  lazy val deck54: List[Card] = Card(BlackJoker) :: Card(WhiteJoker) :: deck52
  lazy val deck52Pairs: List[(Rank.Value, Suit.Value)] = for (r <- ranks.filter(r => r != BlackJoker && r != WhiteJoker); s <- suits.filter(_ != Special)) yield (r, s)
  lazy val deck54Pairs: List[(Rank.Value, Suit.Value)] = (BlackJoker, Special) ::(WhiteJoker, Special) :: deck52Pairs

  /**
   * Shuffle cards deck with condition
   *
   * @param deck - cards
   * @param condition - some condition
   * @return
   */
  def shuffle(deck: List[Card], condition: ShuffleCondition = DefaultCondition): List[Card] = {
    //TODO create recursion guardian
    //TODO @tailrec
    val shuffled = Random.shuffle(deck)
    if (condition.acceptShuffle(shuffled))
      shuffled
    else
      shuffle(shuffled, condition)
  }
}
