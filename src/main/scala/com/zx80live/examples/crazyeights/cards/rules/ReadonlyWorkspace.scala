package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card

/**
 *
 * @author Andrew Proshkin
 */
trait ReadonlyWorkspace {
  def maxPlayersCount: Int

  def stockPile: List[Card]

  def discardPile: List[Card]

  def dealCardsCount: Int

  def currentCard: Card

  def isShuffle: Boolean

}
