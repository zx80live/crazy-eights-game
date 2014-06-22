package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.CardsHelper._

/**
 *
 * @author Andrew Proshkin
 */
trait Crazy8WorkspaceBuilder {

  implicit val shuffleCondition = Crazy8ShuffleCondition

  /**
   * Create shuffled stockpile and discard pile from cards list
   *
   * @param cards - some cards list
   * @return tuple of stockpile and discard pile
   */
  def createWorkspace(cards: List[Card]): Either[Throwable, (List[Card], List[Card])] = {

    //TODO add condition for shorts lists of Eights and Special cards
    cards match {
      case Nil =>
        Left(new IllegalArgumentException("can't create workspace from list of one elements"))
      case head :: Nil =>
        Left(new IllegalArgumentException("can't create workspace from empty cards list"))

      case head :: tail =>
        val shuffled = cards.shuffle
        val stockPile: List[Card] = shuffled.tail
        val discardPile: List[Card] = shuffled.head :: Nil
        Right(stockPile, discardPile)

    }
  }
}
