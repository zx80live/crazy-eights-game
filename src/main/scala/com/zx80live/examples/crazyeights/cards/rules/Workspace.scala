package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException
import com.zx80live.examples.crazyeights.cards.rules.crazy8.WorkspaceEvent

/**
 *
 * @author Andrew Proshkin
 */
trait Workspace {


  implicit val defaultListener: WorkspaceEventListener = new WorkspaceEventListener {
    def onEvent[T](evt: WorkspaceEvent[T]): Unit = {}
  }

  def stockPile: List[Card]

  def discardPile: List[Card]

  def currentCard: Card

  def drawCard(implicit eventListener: WorkspaceEventListener): Option[Card]

  def discardCards(cards: List[Card]): Either[DiscardException, Boolean]

  override def toString: String = {
    s"""Workspace {
      |  stockpile:   $stockPile
      |  discardPile: $discardPile
      |  currentCard: $currentCard
      |}
    """.stripMargin
  }
}

trait WorkspaceEventListener {
  def onEvent[T](evt: WorkspaceEvent[T]): Unit
}
