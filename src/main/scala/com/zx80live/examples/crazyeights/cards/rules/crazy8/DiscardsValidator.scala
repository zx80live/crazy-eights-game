package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException
import com.zx80live.examples.crazyeights.cards.{Card, Suit}

/**
 * Validate player's discards
 *
 * @author Andrew Proshkin
 */
trait DiscardsValidator {
  def validateDiscardByJoker(cards: List[Card]): Either[DiscardException, Boolean] = {
    cards.groupBy(_.suit) match {
      case m if m.size == 1 && m.keys.head == Suit.Special => Right(true)
      case _ => Left(new DiscardException(s"$cards is not contains jokers"))
    }
  }
}
