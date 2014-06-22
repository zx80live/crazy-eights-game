package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.actors.Messages._
import com.zx80live.examples.crazyeights.cards.CardsHelper


/**
 *
 * @author Andrew Proshkin
 */
class Crazy8Workspace extends CardsHelper {
  private var _stockPile: List[Card] = deck54.shuffle(Crazy8ShuffleCondition)
  private var _discardPile: List[Card] = {
    val head = _stockPile.head
    _stockPile = _stockPile.tail
    head
  } :: Nil
  private var _currentCard: Card = _discardPile.head


  def stockPile: List[Card] = _stockPile

  def discardPile: List[Card] = _discardPile

  def currentCard: Card = _discardPile.head

}
