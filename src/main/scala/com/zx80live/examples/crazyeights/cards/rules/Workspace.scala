package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card

/**
 *
 * @author Andrew Proshkin
 */
trait Workspace {

  def stockPile: List[Card]

  def discardPile: List[Card]

  def currentCard: Card

  def drawCard(): Option[Card]

  def discardCards(cards: List[Card])
}
