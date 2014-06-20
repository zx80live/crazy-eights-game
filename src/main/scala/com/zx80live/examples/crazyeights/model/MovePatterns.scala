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
   * Special cards (e.g. "eights", "jokers" will be filtered)
   *
   * @example {{
   *
   *          cards:   [ 2♦][ 3♣][ 2♣][ A♦][ 8♦][ 8♠][ 2♠][ A♣][ ☆ ]
   *          current: [ 4♦]
   *
   *          preferred move patterns:
   *          (0) [ 2♦], [ 2♣], [ 2♠]   <- highest priority (because there are maximum cards for move)
   *          (1) [ A♦], [ A♣]
   *
   *          }}
   *
   * @param current - current card
   * @param cards - player's cards
   * @return preferred move patterns sorted by priority
   */
  def findPreferredBySuit(current: Card, cards: List[Card]): List[List[Card]] = {
    val preferredCardsBySuit = cards.filter(c => c.suit == current.suit && c.rank != Eight && c.suit != Special)
    val cardsGroupedByRank = cards.groupBy(_.rank).toList.sortBy(-_._2.size)

    val byCurrentSuitSorter: (Card, Card) => Boolean = (c1, _) => c1.suit == current.suit

    val result: List[List[Card]] = for {
      c <- cardsGroupedByRank
      pc <- preferredCardsBySuit
      if c._1 == pc.rank
    } yield c._2 sortWith byCurrentSuitSorter

    result
  }

  def findPreferredByRank(current: Card, cards: List[Card]): Option[List[Card]] = ???

  def findEight(current: Card, cards: List[Card]): Option[Card] = ???

  def findJoker(current: Card, cards: List[Card]): Option[Card] = ???

  def findPreferrable(current: Card, cards: List[Card]): Option[List[Card]] = ???
}
