package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.{Rank, Suit, Card}
import com.zx80live.examples.crazyeights.cards.rules.DiscardValidator

/**
 * Validate player's discards
 *
 * @author Andrew Proshkin
 */
trait Crazy8DiscardsValidator extends DiscardValidator {

  def validateFirstJoker(current: Card, cards: List[Card]): Boolean = {
    current match {
      case Card(_, Suit.Special) =>
        validateDiscardByRank(cards.head, cards)
      case _ => false
    }
  }

  def validateDiscard(current: Card, cards: List[Card]): Boolean = {
    validateFirstJoker(current, cards) || validateDiscardByJoker(cards) || validateDiscardByEight(cards) || validateDiscardByRank(current, cards) || validateDiscardBySuit(current, cards)
  }

  def validateDiscardByJoker(cards: List[Card]): Boolean = {
    cards.groupBy(_.suit) match {
      // cards consist of jokers
      case m if m.size == 1 && m.keys.head == Suit.Special => true
      case _ => false
    }
  }

  def validateDiscardByEight(cards: List[Card]): Boolean = {
    validateDiscardByRank(Card(Rank.Eight, Suit.Diamonds), cards)
  }

  def validateDiscardByRank(current: Card, cards: List[Card]): Boolean = {
    cards.groupBy(_.rank) match {
      // cards consist of equal ranks and these ranks equals current.rank
      case m if m.size == 1 && m.keys.head == current.rank => true
      case _ => false
    }
  }

  def validateDiscardBySuit(current: Card, cards: List[Card]): Boolean = {
    cards.groupBy(_.rank) match {
      // cards consist of equal ranks and contains suit such as current.suit
      case m if m.size == 1 && (cards.exists(_.suit == current.suit) || validateDiscardByRank(current, cards)) => true
      case _ => false
    }
  }
}
