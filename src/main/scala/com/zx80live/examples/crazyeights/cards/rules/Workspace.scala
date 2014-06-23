package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DealException, DiscardException, WorkspaceException}
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{DiscardEvent, WorkspaceEvent}
import com.zx80live.examples.crazyeights.cards.{Card, Suit}

/**
 *
 * @author Andrew Proshkin
 */
trait Workspace extends ReadonlyWorkspace {


  implicit val defaultListener: WorkspaceEventListener = new WorkspaceEventListener {
    def onEvent(evt: WorkspaceEvent): Unit = {}
  }


  def deal(playersCount: Int): Either[DealException, List[List[Card]]]


  def drawCard(implicit eventListener: WorkspaceEventListener = defaultListener): Option[Card]

  def discardCards(cards: List[Card]): Either[DiscardException, DiscardEvent]

  //TODO refactoring
  def setCurrentSuit(suit: Suit.Value): Either[WorkspaceException, Boolean]


  override def toString: String = {
    s"""Workspace(currentCard: $currentCard, shuffle: $isShuffle) {
      |  stockpile:   $stockPile
      |  discardPile: $discardPile
      |}
    """.stripMargin
  }
}

trait WorkspaceEventListener {
  def onEvent(evt: WorkspaceEvent): Unit
}
