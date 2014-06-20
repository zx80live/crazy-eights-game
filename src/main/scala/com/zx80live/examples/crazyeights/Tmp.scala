package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.model._

/**
 *
 * @author Andrew Proshkin
 */
object Tmp extends App with MovePatterns {

  import com.zx80live.examples.crazyeights.model.Rank._
  import com.zx80live.examples.crazyeights.model.Suit._

  // 2D, 3C, 2C, AD,
  val cards = Card(Two, Diamonds) :: Card(Three, Clubs) :: Card(Two, Clubs) :: Card(Ace, Diamonds) :: Card(Eight, Diamonds) :: Card(Eight, Spades) :: Card(Two, Spades) :: Card(Ace, Clubs) :: Nil

  cards foreach print
  println()


  def printPreferredPatternsBySuit(current: Card, cards: List[Card]) = {
    println(s"\n$current <- current\npreferred:")
    val preferredBySuit = cards.filter(c => c.suit == current.suit && c.rank != Eight)
    preferredBySuit foreach { c =>
      val movePattern = cards.filter(_.rank == c.rank) sortWith ((c1, _) => c1.suit == current.suit)
      movePattern foreach print
      println()
    }
  }

  printPreferredPatternsBySuit(Card(Four, Diamonds), cards)
  printPreferredPatternsBySuit(Card(Four, Clubs), cards)

}
