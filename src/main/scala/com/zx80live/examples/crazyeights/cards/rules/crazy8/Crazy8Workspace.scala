package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.{Card, CardsHelper}


/**
 * TODO use Set[Card] instead List[Card] in all occurrences
 *
 * @author Andrew Proshkin
 */
class Crazy8Workspace extends Workspace with CardsHelper with Crazy8WorkspaceBuilder {
  private var _stockPile: List[Card] = Nil

  private var _discardPile: List[Card] = Nil

  createWorkspace(deck54) match {
    case Right((s, d)) =>
      _stockPile = s
      _discardPile = d
    case Left(e) => throw e
  }


  def stockPile: List[Card] = _stockPile

  def discardPile: List[Card] = _discardPile

  def currentCard: Card = _discardPile.head

  /**
   * Gets top card from stockpile.
   * If stockpile is empty then stockpile := shuffle(discardPile)
   *
   * @return
   */
  def drawCard(): Option[Card] = {
    _stockPile match {
      case head :: tail =>
        _stockPile = tail
        Some(head)
      case Nil =>
        createWorkspace(deck54) match {
          case Right((s, d)) =>
            //TODO analyze recursion and add guardian
            _stockPile = s
            _discardPile = d
            drawCard()

          // stockpile and discard pile are empty
          case Left(e) => None
        }
    }
  }

  override def discardCards(cards: List[Card]): Unit = ???
}
