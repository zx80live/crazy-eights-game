package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.rules.Workspace
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DiscardException, EmptyCardsDiscardException}
import com.zx80live.examples.crazyeights.cards.{Card, CardsHelper, Suit}


/**
 * TODO use Set[Card] instead List[Card] in all occurrences
 *
 * @author Andrew Proshkin
 */
class Crazy8Workspace extends Workspace with CardsHelper with Crazy8WorkspaceBuilder with Crazy8MovePatterns {

  import scala.language.reflectiveCalls

  private var _stockPile: List[Card] = Nil

  private var _discardPile: List[Card] = Nil

  createWorkspace(deck54) match {
    case Right((s, d)) =>
      _stockPile = s
      _discardPile = d
    case Left(e) => throw e
  }


  override def stockPile: List[Card] = _stockPile

  override def discardPile: List[Card] = _discardPile

  override def currentCard: Card = _discardPile.head

  /**
   * Gets top card from stockpile.
   * If stockpile is empty then stockpile := shuffle(discardPile)
   *
   * @param eventListener - listener for handling inner events from drawCards method
   * @return Some[Card] or None if stockpile and discard pile are empty
   */
  override def drawCard(implicit eventListener: WorkspaceEventListener): Option[Card] = {
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

            eventListener.onEvent(new RecreateStockpileEvent)
            drawCard(eventListener)

          case Left(e) => None
        }
    }
  }

  /**
   * Discard player's cards.
   *
   * @param cards - player's cards
   * @return
   */
  override def discardCards(cards: List[Card]): Either[DiscardException, Boolean] = {
    cards match {
      case Nil => Left(new EmptyCardsDiscardException)
      case _ =>
        // check can move by rank
        // check can move by suit
        // check can move by eight
        // check can move by joker

        _discardPile = cards ::: _discardPile
        Right(true)
    }
  }

  //def validateDiscardByRank(cards: List[Card], current: Card): Either[DiscardException, Boolean] = {
  //    cards.groupBy(_.rank) match {
  //      case m if m.size > 1 &&  =>
  //    }
  //}
  //  def validateDiscardBySuit(cards: List[Card], current: Card): Either[DiscardException, Boolean] = ???
  //
  //  def validateDiscardByEight(cards: List[Card], current: Card): Either[DiscardException, Boolean] = ???
  //


  override def validateDiscard(cards: List[Card]): Either[DiscardException, Boolean] = ???
}
