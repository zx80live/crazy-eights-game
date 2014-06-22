package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException

/**
 *
 * @author Andrew Proshkin
 */
trait Workspace {
  type WorkspaceEvent = String

  type WorkspaceEventListener = {def onEvent(evt: WorkspaceEvent): Unit}

  implicit val defaultListener: WorkspaceEventListener = new {
    def onEvent(evt: WorkspaceEvent): Unit = {}
  }

  def stockPile: List[Card]

  def discardPile: List[Card]

  def currentCard: Card

  def drawCard(implicit eventListener: WorkspaceEventListener): Option[Card]

  def discardCards(cards: List[Card]): Either[DiscardException, Boolean]
}
