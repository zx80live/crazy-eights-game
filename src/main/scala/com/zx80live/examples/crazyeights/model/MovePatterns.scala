package com.zx80live.examples.crazyeights.model

/**
 *
 * @author Andrew Proshkin
 */
trait MovePatterns {

  import com.zx80live.examples.crazyeights.model.Rank.Eight
  import com.zx80live.examples.crazyeights.model.Suit.Special

  /**
   * Find preferred move patterns by current suit.
   * Special cards (e.g. "eights", "jokers" will be filtered).
   *
   * @example {{
   *
   *          cards:   [ 2♦][ 3♣][ 2♣][ A♦][ 8♦][ 8♠][ 2♠][ A♣][ ☆ ]
   *          current: [ 4♣]
   *
   *          preferred move patterns:
   *          (0) [ 2♣][ 2♦][ 2♠]   <- highest priority (because there are maximum cards for move)
   *          (1) [ A♣][ A♦]
   *          (2) [ 3♣]             <- lowest priority
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
   *          cards:   [ 2♦][ 3♣][ 2♣][ A♦][ 8♦][ 8♠][ 2♠][ A♣][ ☆ ]
   *          current: [ A♠]
   *
   *          preferred move pattern: [ A♦][ A♣]
   *          }}
   *
   * @param current - current card
   * @param cards - player's cards
   * @return preferred move pattern
   */
  def findPreferredByRank(current: Card, cards: List[Card]): List[Card] = {
    cards.filter(_.rank == current.rank)
  }

  /**
   * Find first eight
   *
   * @param current - current card
   * @param cards - player's cards
   * @return first eight option
   */
  def findEight(current: Card, cards: List[Card]): Option[Card] = cards.find(_.rank == Eight)

  /**
   * Find first joker
   *
   * @param current - current card
   * @param cards - player's cards
   * @return first joker option
   */
  def findJoker(current: Card, cards: List[Card]): Option[Card] = cards.find(_.suit == Special)

  def findPreferred(current: Card, cards: List[Card]): Option[List[Card]] = ???
}
