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
  val cards = Card(Two, Diamonds) :: Card(Three, Clubs) :: Card(Two, Clubs) :: Card(Ace, Diamonds) :: Card(Eight, Diamonds) :: Card(Eight, Spades) :: Card(Two, Spades) :: Card(Ace, Clubs) :: Card(WhiteJoker) :: Nil
  val cards2 = Card(Two, Diamonds) :: Card(Three, Clubs) :: Card(Two, Clubs) :: Card(Ace, Diamonds) :: Card(Eight, Diamonds) :: Card(Ace, Hearts) :: Nil

  cards foreach print
  println()


  println(s"current: ${Card(Four, Diamonds)}")
  println(findPreferred(Card(Four, Diamonds), cards))
  println(s"current: ${Card(Four, Hearts)}")
  println(findPreferred(Card(Four, Hearts), cards))
  //  println(s"current: ${Card(Four, Diamonds)}")
//  findPreferredBySuit(Card(Four, Diamonds), cards) foreach println
//  println(s"current: ${Card(Four, Clubs)}")
//  findPreferredBySuit(Card(Four, Clubs), cards) foreach println
//  println(s"current: ${Card(Four, Spades)}")
//  findPreferredBySuit(Card(Four, Spades), cards) foreach println
//
//  println("by rank:")
//  println(s"current: ${Card(Ace, Spades)}")
//  findPreferredByRank(Card(Ace, Spades), cards) foreach print
//  println()
//  println(s"current: ${Card(Ten, Spades)}")
//  println(findPreferredByRank(Card(Ten, Spades), cards))
}
