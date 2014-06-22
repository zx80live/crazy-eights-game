package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.cards.rules.Crazy8MovePatterns
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}

/**
 *
 * @author Andrew Proshkin
 */
object Tmp extends App with Crazy8MovePatterns {

  import com.zx80live.examples.crazyeights.cards.Rank._
  import com.zx80live.examples.crazyeights.cards.Suit._

  // 2D, 3C, 2C, AD,
  val cards = Card(Two, Diamonds) :: Card(Three, Clubs) :: Card(Two, Clubs) :: Card(Ace, Diamonds) :: Card(Eight, Diamonds) :: Card(Eight, Spades) :: Card(Two, Spades) :: Card(Ace, Clubs) :: Card(WhiteJoker) :: Card(BlackJoker) :: Nil
  val cards2 = Card(Two, Diamonds) :: Card(Three, Clubs) :: Card(Two, Clubs) :: Card(Ace, Diamonds) :: Card(Eight, Diamonds) :: Card(Ace, Hearts) :: Nil

  //  cards foreach print
  //  println()


  //  println(s"current: ${Card(Four, Diamonds)}")
  //  println(findPreferred(Card(Four, Diamonds), cards))
  //  println(s"current: ${Card(Four, Hearts)}")
  //  println(findPreferred(Card(Four, Hearts), cards))
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


  val o1 = None
  val o2 = Some(List())


  println {
    Seq(o1, o2).flatten match {
      case Seq(Nil, b) => b
      case Seq(b, Nil) => b
      case Seq(a, b) if a.length > b.length => a
      case Seq(a, b) if a.length < b.length => b
      case Seq(Nil, Nil) => None
      case Seq(Nil) => None
      case Seq(a) => a

      case _ => None
    }
  }


}
