package com.zx80live.examples.crazyeights.model

import scala.collection.generic.FilterMonadic

/**
 *
 * @author Andrew Proshkin
 */
trait MovePatterns {

  import Rank._
  import Suit._

  def findPreferablePatternBySuit(current: Card, cards: List[Card]): Option[List[Card]] = ???

  def findPreferablePatternByRank(current: Card, cards: List[Card]): Option[List[Card]] = ???


}
