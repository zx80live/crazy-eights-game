package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}

/**
 * Validate player's discards
 *
 * @author Andrew Proshkin
 */
trait DiscardsValidator {
  def validateDiscardByJoker(cards: List[Card]): Boolean = {
    cards.groupBy(_.suit) match {
      case m if m.size == 1 && m.keys.head == Suit.Special => true
      case _ => false
    }
  }

  def validateDiscardByEight(cards: List[Card]): Boolean = {
    cards.groupBy(_.rank) match {
      case m if m.size == 1 && m.keys.head == Rank.Eight => true
      case _ => false
    }
  }
}
