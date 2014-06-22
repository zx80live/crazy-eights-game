package com.zx80live.examples.crazyeights.cards

/**
 * Card model
 *
 * example {{{
 *   Card(Rank.Seven, Suit.Diamonds)
 *   Card(Rank.WhiteJoker)
 * }}}
 *
 * @author Andrew Proshkin
 */
case class Card(rank: Rank.Value, suit: Suit.Value = Suit.Special) extends UnicodeView {
  if ((rank == Rank.WhiteJoker || rank == Rank.BlackJoker) && suit != Suit.Special)
    throw new IllegalArgumentException(s"Joker must not have suit: $this")
}

/**
 * Card's suit
 *
 * Suit.Special used for Joker ranks
 */
object Suit extends Enumeration {
  type Suit = Value
  val Spades, Hearts, Diamonds, Clubs, Special = Value
}

/**
 * Card's rank for 54-deck
 */
object Rank extends Enumeration {
  type Rank = Value
  val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace, BlackJoker, WhiteJoker = Value
}


