package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.{Rank, Card}

/**
 *
 * @author Andrew Proshkin
 */
trait DiscardValidator {
  def validateDiscard(current: Card, cards: List[Card]): Boolean

  def isRank(cards: List[Card], rank: Rank.Value): Boolean = {
    cards.groupBy(_.rank) match {
      // cards consist of equal ranks and these ranks equals current.rank
      case m if m.size == 1 && m.keys.head == rank => true
      case _ => false
    }
  }
}
