package com.zx80live.examples.crazyeights.cards.dsl

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
