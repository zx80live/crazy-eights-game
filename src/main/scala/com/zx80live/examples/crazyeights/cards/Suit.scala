package com.zx80live.examples.crazyeights.cards

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
