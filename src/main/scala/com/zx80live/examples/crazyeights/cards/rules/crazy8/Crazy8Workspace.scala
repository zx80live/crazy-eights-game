package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.DiscardException
import com.zx80live.examples.crazyeights.cards.rules.{Workspace, WorkspaceEventListener}


/**
 * TODO ERROR! currentCard may be Nine!
 *
 * TODO use Set[Card] instead List[Card] in all occurrences
 *
 * @author Andrew Proshkin
 */
class Crazy8Workspace(cards: List[Card] = deck54) extends Workspace with Crazy8WorkspaceBuilder with Crazy8MovePatterns with Crazy8DiscardsValidator {

  /**
   * used for structural types
   * @see Workspace#WorkspaceEventListener
   */

  import scala.language.reflectiveCalls

  private var _stockPile: List[Card] = Nil

  private var _discardPile: List[Card] = Nil

  createWorkspace(cards) match {
    case Right((s, d)) =>
      _stockPile = s
      _discardPile = d
    case Left(e) => throw e
  }


  // TODO test
  override def stockPile: List[Card] = _stockPile

  // TODO test
  override def discardPile: List[Card] = _discardPile

  // TODO test
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

  /** TODO test
    * TODO event (Joker, Eight, Success)
    * Discard player's cards.
    *
    * @param cards - player's cards
    * @return
    */
  override def discardCards(cards: List[Card]): Either[DiscardException, Boolean] = {
    if (!validateDiscard(currentCard, cards)) {
      Left(new DiscardException(s"$cards is not valid for current $currentCard"))
    } else {
      _discardPile = cards ::: _discardPile
      Right(true)
    }
  }



}
