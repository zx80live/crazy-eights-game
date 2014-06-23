package com.zx80live.examples.crazyeights.cards.rules

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DealException, DiscardException}
import com.zx80live.examples.crazyeights.cards.rules.crazy8.{DiscardEvent, WorkspaceEvent}

/**
 *
 * @author Andrew Proshkin
 */
trait Workspace extends ReadonlyWorkspace {


  implicit val defaultListener: WorkspaceEventListener = new WorkspaceEventListener {
    def onEvent(evt: WorkspaceEvent): Unit = {}
  }


  def deal(playersCount: Int): Either[DealException, List[List[Card]]]


  def drawCard(implicit eventListener: WorkspaceEventListener): Option[Card]

  def discardCards(cards: List[Card]): Either[DiscardException, DiscardEvent]


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
