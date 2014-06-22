package com.zx80live.examples.crazyeights.cards

import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.IllegalCardFormatException

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
    throw new IllegalCardFormatException(s"Joker must not have suit: $this")
}

/**
 * Card's suit
 *
 * Suit.Special used for Joker ranks
 */
object Suit extends Enumeration {
  type Suit = Value
  val Spades = Value("S")
  val Hearts = Value("H")
  val Diamonds = Value("D")
  val Clubs = Value("C")
  val Special = Value("")
}

/**
 * Card's rank for 54-deck
 */
object Rank extends Enumeration {
  type Rank = Value
  val Two = Value("2")
  val Three = Value("3")
  val Four = Value("4")
  val Five = Value("5")
  val Six = Value("6")
  val Seven = Value("7")
  val Eight = Value("8")
  val Nine = Value("9")
  val Ten = Value("10")
  val Jack = Value("J")
  val Queen = Value("Q")
  val King = Value("K")
  val Ace = Value("A")
  val BlackJoker = Value("BJ")
  val WhiteJoker = Value("WJ")
}


