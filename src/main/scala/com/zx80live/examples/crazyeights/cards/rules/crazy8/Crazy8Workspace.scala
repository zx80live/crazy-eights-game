package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Exceptions.{DealException, DiscardException, WorkspaceException}
import com.zx80live.examples.crazyeights.cards.rules.{Workspace, WorkspaceEventListener}
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}


/**
 * TODO ERROR! currentCard may be Nine!
 *
 * TODO use Set[Card] instead List[Card] in all occurrences
 *
 * @author Andrew Proshkin
 */
class Crazy8Workspace(cards: List[Card] = deck54, shuffle: Boolean = true) extends Workspace with Crazy8WorkspaceBuilder with Crazy8MovePatterns with Crazy8DiscardsValidator {

  /**
   * used for structural types
   * @see Workspace#WorkspaceEventListener
   */

  import scala.language.reflectiveCalls

  private var _stockPile: List[Card] = Nil

  private var _discardPile: List[Card] = Nil

  createWorkspace(cards, shuffle) match {
    case Right((s, d)) =>
      _stockPile = s
      _discardPile = d
    case Left(e) => throw e
  }


  override def isShuffle: Boolean = shuffle

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
        createWorkspace(_discardPile, shuffle) match {
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
  override def discardCards(cards: List[Card]): Either[DiscardException, DiscardEvent] = {
    if (!validateDiscard(currentCard, cards)) {
      Left(new DiscardException(s"$cards is not valid for current $currentCard"))
    } else {
      _discardPile = cards ::: _discardPile

      if (validateDiscardByEight(cards))
        Right(new EightDiscardEvent)
      else if (validateDiscardByJoker(cards))
        Right(new JokerDiscardEvent)
      else
        Right(new SuccessDiscardEvent)
    }
  }


  override def deal(playersCount: Int): Either[DealException, List[List[Card]]] = {
    if (playersCount > maxPlayersCount || playersCount <= 0) {
      Left(new DealException(s"players count must be between(1, $maxPlayersCount), current playersCount = $playersCount"))

    } else {
      var cards: List[List[Card]] = Nil
      for (i <- 1 to playersCount) {
        var playerCards: List[Card] = Nil
        for (ci <- 1 to dealCardsCount)
          playerCards = drawCard.get :: playerCards

        cards = playerCards :: cards
      }
      Right(cards)
    }
  }

  override def maxPlayersCount: Int = 4

  override def dealCardsCount: Int = 8

  //TODO refactoring
  //TODO test
  override def setCurrentSuit(suit: Suit.Value): Either[WorkspaceException, Boolean] = {
    currentCard match {
      case Card(Rank.Eight, _) =>
        _discardPile = Card(Rank.Eight, suit) :: _discardPile.tail
        Right(true)
      case _ => Left(new WorkspaceException("can't change suit of current card that is not eight"))
    }
  }
}
