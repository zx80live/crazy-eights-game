package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}


/**
 * Search move patterns for crazy eights game
 *
 * @author Andrew Proshkin
 */
trait Crazy8MovePatterns {

  import com.zx80live.examples.crazyeights.cards.Rank.Eight
  import com.zx80live.examples.crazyeights.cards.Suit.Special
  import com.zx80live.examples.crazyeights.util.CollectionUtils._

  /**
   * Find preferred move patterns by current suit.
   * Special cards (e.g. "eights", "jokers" will be filtered).
   *
   * @example {{
   *
   *          cards:   2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆
   *          current: 4♣
   *
   *          preferred move patterns:
   *          (0) 2♣, 2♦, 2♠   <- highest priority (because there are maximum cards for move)
   *          (1) A♣, A♦
   *          (2) 3♣           <- lowest priority
   *
   *          }}
   *
   * @param current - current card
   * @param cards - player's cards
   * @return preferred move patterns sorted by priority
   */
  def findPreferredBySuit(current: Card, cards: List[Card]): List[List[Card]] = {
    // select player's cards by current suit (without eights and jokers)
    val preferredCardsBySuit = cards.filter(c => c.suit == current.suit && c.rank != Eight && c.suit != Special)
    // group player's cards by rank and sort by max group size
    val cardsGroupedByRank = cards.groupBy(_.rank).toList.sortBy(-_._2.size)
    // lambda for sorter (current card suit will be first)
    val byCurrentSuitSorter: (Card, Card) => Boolean = (c1, _) => c1.suit == current.suit

    // join user cards and preferred cards with equal ranks
    val result: List[List[Card]] = for {
      c <- cardsGroupedByRank
      pc <- preferredCardsBySuit
      if c._1 == pc.rank
    } yield c._2 sortWith byCurrentSuitSorter

    result
  }

  /**
   * Find preferred move pattern by current rank.
   * Special cards (e.g. "eights", "jokers" will be filtered).
   *
   * @example {{
   *
   *          cards:   2♦, 3♣, 2♣, A♦, 8♦, 8♠, 2♠, A♣, ☆
   *          current: A♠
   *
   *          preferred move pattern: A♦, A♣
   *          }}
   *
   * @param current - current card
   * @param cards - player's cards
   * @return preferred move pattern
   */
  def findPreferredByRank(current: Card, cards: List[Card]): Option[List[Card]] =
    cards.filter(_.rank == current.rank) match {
      case List() => None
      case xs => Some(xs)
    }


  /**
   * Find first eight
   *
   * @param cards - player's cards
   * @return first eight option
   */
  def findEight(cards: List[Card]): Option[Card] = cards.find(_.rank == Eight)


  /**
   * Find first joker
   *
   * @param cards - player's cards
   * @return first joker option
   */
  def findJoker(cards: List[Card]): Option[Card] = cards.find(_.suit == Special)


  /**
   * Find best preferred move pattern from available patterns
   *
   * @param curr - current card
   * @param cards - player's cards
   * @return best preferred move pattern
   */
  def findPreferred(curr: Card, cards: List[Card]): List[Card] = {
    val preferredBecauseJoker: Option[List[Card]] =
      if (curr.suit == Special)
        findAnyNonSpecial(cards)
      else
        None

    val preferredBySuit: Option[List[Card]] = findPreferredBySuit(curr, cards).headOption
    val preferredByRank: Option[List[Card]] = findPreferredByRank(curr, cards)
    val maxPreferred: Option[List[Card]] = maxList(preferredBySuit, preferredByRank)

    val result = preferredBecauseJoker orElse maxPreferred orElse findEight(cards) orElse findJoker(cards)

    result match {
      case Some(x: Card) => List(x)
      case Some(xs: List[_]) if xs.forall(_.isInstanceOf[Card]) => xs.asInstanceOf[List[Card]]
      case _ => List()
    }
  }

  /**
   * Used if there was some joker
   *
   * //TODO optimize: get any non-preferred card (instead duplicate cards)
   *
   * @param cards - player cards
   * @return
   */
  def findAnyNonSpecial(cards: List[Card]): Option[List[Card]] = {
    cards.find(c => c.rank != Eight && c.suit != Special) match {
      case Some(xs) => Some(List(xs))
      case _ => None
    }
  }
}
