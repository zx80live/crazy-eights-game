import java.io.Serializable

import com.zx80live.examples.crazyeights._
import Rank._
import Suit._

import scala.collection.immutable.SortedSet
import scala.util.Random

val c: Card = Card(Ace, Diamonds)

println(c)
println(Card(Two, Clubs))
//Deck.deck.reverse foreach println
//Deck.deck.length

//val shuffle: Seq[Card] = Random.shuffle(Deck.deck)
//shuffle foreach { c =>
//  println(CardConsoleView(c))
//}


def createDeck54: List[(Rank.Value, Suit.Value)] = {
  val ranks = Rank.values.toList.filterNot(r => r.eq(Rank.BlackJoker) || r.eq(Rank.WhiteJoker))
  val suits = Suit.values.toList.filterNot(_.eq(Suit.Special))
  val deck52 = for (r <- ranks; s <- suits) yield (r, s)

  (Rank.BlackJoker, Suit.Special) ::(Rank.WhiteJoker, Suit.Special) :: deck52
}

val deck54: List[(Rank.Value, Suit.Value)] = createDeck54
deck54 foreach println
Random.shuffle(deck54) foreach println

