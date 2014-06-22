package com.zx80live.examples.crazyeights.cards

/**
 *
 * @author Andrew Proshkin
 */
case class Card(rank: Rank.Value, suit: Suit.Value = Suit.Special) extends UnicodeView

object Suit extends Enumeration {
  type Suit = Value
  val Spades, Hearts, Diamonds, Clubs, Special = Value
}

object Rank extends Enumeration {
  type Rank = Value
  val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace, BlackJoker, WhiteJoker = Value
}


