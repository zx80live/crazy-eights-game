package com.zx80live.examples.crazyeights


/**
 *
 * @author Andrew Proshkin
 */
case class Card(rank: Rank.Value, suit: Suit.Value = Suit.Special)

object Suit extends Enumeration {
  type Suit = Value
  val Spades, Hearts, Diamonds, Clubs, Special = Value
}

object Rank extends Enumeration {
  type Rank = Value
  val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace, BlackJoker, WhiteJoker = Value
}


object Deck {

  import Rank._
  import Suit._




  val deck: Seq[Card] = Seq(
    Card(Two, Spades), Card(Two, Hearts), Card(Two, Diamonds), Card(Two, Clubs),
    Card(Three, Spades), Card(Three, Hearts), Card(Three, Diamonds), Card(Three, Clubs),
    Card(Four, Spades), Card(Four, Hearts), Card(Four, Diamonds), Card(Four, Clubs),
    Card(Five, Spades), Card(Five, Hearts), Card(Five, Diamonds), Card(Five, Clubs),
    Card(Six, Spades), Card(Six, Hearts), Card(Six, Diamonds), Card(Six, Clubs),
    Card(Seven, Spades), Card(Seven, Hearts), Card(Seven, Diamonds), Card(Seven, Clubs),
    Card(Eight, Spades), Card(Eight, Hearts), Card(Eight, Diamonds), Card(Eight, Clubs),
    Card(Nine, Spades), Card(Nine, Hearts), Card(Nine, Diamonds), Card(Nine, Clubs),
    Card(Ten, Spades), Card(Ten, Hearts), Card(Ten, Diamonds), Card(Ten, Clubs),
    Card(Jack, Spades), Card(Jack, Hearts), Card(Jack, Diamonds), Card(Jack, Clubs),
    Card(Queen, Spades), Card(Queen, Hearts), Card(Queen, Diamonds), Card(Queen, Clubs),
    Card(King, Spades), Card(King, Hearts), Card(King, Diamonds), Card(King, Clubs),
    Card(Ace, Spades), Card(Ace, Hearts), Card(Ace, Diamonds), Card(Ace, Clubs),
    Card(BlackJoker), Card(WhiteJoker)
  )

  val cardsTuple = Seq(
    (Two, Spades), (Two, Hearts), (Two, Diamonds), (Two, Clubs),
    (Three, Spades), (Three, Hearts), (Three, Diamonds), (Three, Clubs),
    (Four, Spades), (Four, Hearts), (Four, Diamonds), (Four, Clubs),
    (Five, Spades), (Five, Hearts), (Five, Diamonds), (Five, Clubs),
    (Six, Spades), (Six, Hearts), (Six, Diamonds), (Six, Clubs),
    (Seven, Spades), (Seven, Hearts), (Seven, Diamonds), (Seven, Clubs),
    (Eight, Spades), (Eight, Hearts), (Eight, Diamonds), (Eight, Clubs),
    (Nine, Spades), (Nine, Hearts), (Nine, Diamonds), (Nine, Clubs),
    (Ten, Spades), (Ten, Hearts), (Ten, Diamonds), (Ten, Clubs),
    (Jack, Spades), (Jack, Hearts), (Jack, Diamonds), (Jack, Clubs),
    (Queen, Spades), (Queen, Hearts), (Queen, Diamonds), (Queen, Clubs),
    (King, Spades), (King, Hearts), (King, Diamonds), (King, Clubs),
    (Ace, Spades), (Ace, Hearts), (Ace, Diamonds), (Ace, Clubs),
    BlackJoker, WhiteJoker
  )


  deck
}

