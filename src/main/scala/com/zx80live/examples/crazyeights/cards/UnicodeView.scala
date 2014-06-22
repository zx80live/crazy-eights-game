package com.zx80live.examples.crazyeights.cards

/**
 *
 * @author Andrew Proshkin
 */
trait UnicodeView {
  this: Card =>

  override def toString = {
    import com.zx80live.examples.crazyeights.cards.Rank._
    import com.zx80live.examples.crazyeights.cards.Suit._

    val s: String = suit match {
      case Spades => "♠"
      case Hearts => "♥"
      case Diamonds => "♦"
      case Clubs => "♣"
      case Special => ""
    }

    val r: String = rank match {
      case Two => " 2"
      case Three => " 3"
      case Four => " 4"
      case Five => " 5"
      case Six => " 6"
      case Seven => " 7"
      case Eight => " 8"
      case Nine => " 9"
      case Ten => "10"
      case Jack => " J"
      case Queen => " Q"
      case King => " K"
      case Ace => " A"
      case BlackJoker => " ★ "
      case WhiteJoker => " ☆ "
    }
    s"$r$s"
  }
}
