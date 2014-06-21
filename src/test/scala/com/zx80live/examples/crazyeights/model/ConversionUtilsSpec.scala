package com.zx80live.examples.crazyeights.model

import com.zx80live.examples.crazyeights.model.ConversionUtils._
import com.zx80live.examples.crazyeights.model.Rank._
import com.zx80live.examples.crazyeights.model.Suit._
import org.scalatest._

/**
 * Specification for ConversionUtils
 *
 * @author Andrew Proshkin
 */
class ConversionUtilsSpec extends WordSpec with Matchers {

  val rankStrings: List[String] = "2" :: "3" :: "4" :: "5" :: "6" :: "7" :: "8" :: "9" :: "10" :: "J" :: "Q" :: "K" :: "A" :: "★" :: "☆" :: Nil
  val suitStrings: List[String] = "♠" :: "♥" :: "♦" :: "♣" :: Nil
  val deck54Strings: List[String] = "★" :: "☆" :: (for (r <- rankStrings.filter(r => r != "★" && r != "☆"); s <- suitStrings) yield s"$r$s")

  "string2Rank" when {
    "converts correct rankStrings into Some(_)" in {
      rankStrings foreach (r => assert(string2Rank(r) != None))
    }

    "converts correct string values into Some(Rank.Value)" in {
      string2Rank("2") shouldEqual Some(Two)
      string2Rank("3") shouldEqual Some(Three)
      string2Rank("4") shouldEqual Some(Four)
      string2Rank("5") shouldEqual Some(Five)
      string2Rank("6") shouldEqual Some(Six)
      string2Rank("7") shouldEqual Some(Seven)
      string2Rank("8") shouldEqual Some(Eight)
      string2Rank("9") shouldEqual Some(Nine)
      string2Rank("10") shouldEqual Some(Ten)
      string2Rank("J") shouldEqual Some(Jack)
      string2Rank("Q") shouldEqual Some(Queen)
      string2Rank("K") shouldEqual Some(King)
      string2Rank("A") shouldEqual Some(Ace)
      string2Rank("BJ") shouldEqual Some(BlackJoker)
      string2Rank("★") shouldEqual Some(BlackJoker)
      string2Rank("WJ") shouldEqual Some(WhiteJoker)
      string2Rank("☆") shouldEqual Some(WhiteJoker)
    }

    "converts wrong string values into None" in {
      string2Rank("") shouldEqual None
      string2Rank(" ") shouldEqual None
      string2Rank("1") shouldEqual None
      string2Rank("11") shouldEqual None
      string2Rank("ABC") shouldEqual None
      string2Rank("A B C ") shouldEqual None
      string2Rank(" 1 2 3 4  5") shouldEqual None
    }

    "convert correct string values into Some(Rank.Value) implicitly" in {
      ("2": Option[Rank.Value]) shouldEqual Some(Two)
      ("3": Option[Rank.Value]) shouldEqual Some(Three)
      ("4": Option[Rank.Value]) shouldEqual Some(Four)
      ("5": Option[Rank.Value]) shouldEqual Some(Five)
      ("6": Option[Rank.Value]) shouldEqual Some(Six)
      ("7": Option[Rank.Value]) shouldEqual Some(Seven)
      ("8": Option[Rank.Value]) shouldEqual Some(Eight)
      ("9": Option[Rank.Value]) shouldEqual Some(Nine)
      ("10": Option[Rank.Value]) shouldEqual Some(Ten)
      ("J": Option[Rank.Value]) shouldEqual Some(Jack)
      ("Q": Option[Rank.Value]) shouldEqual Some(Queen)
      ("K": Option[Rank.Value]) shouldEqual Some(King)
      ("A": Option[Rank.Value]) shouldEqual Some(Ace)
      ("BJ": Option[Rank.Value]) shouldEqual Some(BlackJoker)
      ("★": Option[Rank.Value]) shouldEqual Some(BlackJoker)
      ("WJ": Option[Rank.Value]) shouldEqual Some(WhiteJoker)
      ("☆": Option[Rank.Value]) shouldEqual Some(WhiteJoker)
    }

    "convert wrong string values into None implicitly" in {
      ("": Option[Rank.Value]) shouldEqual None
      (" ": Option[Rank.Value]) shouldEqual None
      ("1": Option[Rank.Value]) shouldEqual None
      ("11": Option[Rank.Value]) shouldEqual None
      ("22": Option[Rank.Value]) shouldEqual None
      ("AA": Option[Rank.Value]) shouldEqual None
      ("A A": Option[Rank.Value]) shouldEqual None
      ("ABC": Option[Rank.Value]) shouldEqual None
      ("A B C": Option[Rank.Value]) shouldEqual None
      (" 1 2 3 4  5": Option[Rank.Value]) shouldEqual None
    }
  }

  "string2Suit" when {
    "converts correct suitStrings into Some(_)" in {
      suitStrings foreach (r => assert(string2Suit(r) != None))
    }

    "converts correct string values into Some(Suit.Value)" in {
      string2Suit("S") shouldEqual Some(Spades)
      string2Suit("♠") shouldEqual Some(Spades)
      string2Suit("H") shouldEqual Some(Hearts)
      string2Suit("♥") shouldEqual Some(Hearts)
      string2Suit("D") shouldEqual Some(Diamonds)
      string2Suit("♦") shouldEqual Some(Diamonds)
      string2Suit("C") shouldEqual Some(Clubs)
      string2Suit("♣") shouldEqual Some(Clubs)
    }

    "converts wrong string values into None" in {
      string2Suit("") shouldEqual None
      string2Suit(" ") shouldEqual None
      string2Suit("1") shouldEqual None
      string2Suit("2") shouldEqual None
      string2Suit("11") shouldEqual None
      string2Suit(" 1 1    1") shouldEqual None
      string2Suit("SS") shouldEqual None
      string2Suit("SH") shouldEqual None
      string2Suit("HH") shouldEqual None
      string2Suit("DD") shouldEqual None
      string2Suit("CC") shouldEqual None
    }

    "converts correct string values into Some(Suit.Value) implicitly" in {
      ("S": Option[Suit.Value]) shouldEqual Some(Spades)
      ("♠": Option[Suit.Value]) shouldEqual Some(Spades)
      ("H": Option[Suit.Value]) shouldEqual Some(Hearts)
      ("♥": Option[Suit.Value]) shouldEqual Some(Hearts)
      ("D": Option[Suit.Value]) shouldEqual Some(Diamonds)
      ("♦": Option[Suit.Value]) shouldEqual Some(Diamonds)
      ("C": Option[Suit.Value]) shouldEqual Some(Clubs)
      ("♣": Option[Suit.Value]) shouldEqual Some(Clubs)
    }

    "converts wrong string values into None implicitly" in {
      ("": Option[Suit.Value]) shouldEqual None
      (" ": Option[Suit.Value]) shouldEqual None
      ("1": Option[Suit.Value]) shouldEqual None
      ("2": Option[Suit.Value]) shouldEqual None
      ("11": Option[Suit.Value]) shouldEqual None
      (" 1 1    1": Option[Suit.Value]) shouldEqual None
      ("SS": Option[Suit.Value]) shouldEqual None
      ("SH": Option[Suit.Value]) shouldEqual None
      ("HH": Option[Suit.Value]) shouldEqual None
      ("DD": Option[Suit.Value]) shouldEqual None
      ("CC": Option[Suit.Value]) shouldEqual None
    }
  }


  "string2Card" when {
    "converts string values from deck54Strings into Some(Card)" in {
      deck54Strings foreach (c => assert(string2Card(c) != None))
    }

    "converts string values into Some(Card)" in {
      string2Card("★") shouldEqual Some(Card(BlackJoker))
      string2Card("☆") shouldEqual Some(Card(WhiteJoker))
      string2Card("2♠") shouldEqual Some(Card(Two, Spades))
      string2Card("2♥") shouldEqual Some(Card(Two, Hearts))
      string2Card("2♦") shouldEqual Some(Card(Two, Diamonds))
      string2Card("2♣") shouldEqual Some(Card(Two, Clubs))
      string2Card("3♠") shouldEqual Some(Card(Three, Spades))
      string2Card("3♥") shouldEqual Some(Card(Three, Hearts))
      string2Card("3♦") shouldEqual Some(Card(Three, Diamonds))
      string2Card("3♣") shouldEqual Some(Card(Three, Clubs))
      string2Card("4♠") shouldEqual Some(Card(Four, Spades))
      string2Card("4♥") shouldEqual Some(Card(Four, Hearts))
      string2Card("4♦") shouldEqual Some(Card(Four, Diamonds))
      string2Card("4♣") shouldEqual Some(Card(Four, Clubs))
      string2Card("5♠") shouldEqual Some(Card(Five, Spades))
      string2Card("5♥") shouldEqual Some(Card(Five, Hearts))
      string2Card("5♦") shouldEqual Some(Card(Five, Diamonds))
      string2Card("5♣") shouldEqual Some(Card(Five, Clubs))
      string2Card("6♠") shouldEqual Some(Card(Six, Spades))
      string2Card("6♥") shouldEqual Some(Card(Six, Hearts))
      string2Card("6♦") shouldEqual Some(Card(Six, Diamonds))
      string2Card("6♣") shouldEqual Some(Card(Six, Clubs))
      string2Card("7♠") shouldEqual Some(Card(Seven, Spades))
      string2Card("7♥") shouldEqual Some(Card(Seven, Hearts))
      string2Card("7♦") shouldEqual Some(Card(Seven, Diamonds))
      string2Card("7♣") shouldEqual Some(Card(Seven, Clubs))
      string2Card("8♠") shouldEqual Some(Card(Eight, Spades))
      string2Card("8♥") shouldEqual Some(Card(Eight, Hearts))
      string2Card("8♦") shouldEqual Some(Card(Eight, Diamonds))
      string2Card("8♣") shouldEqual Some(Card(Eight, Clubs))
      string2Card("9♠") shouldEqual Some(Card(Nine, Spades))
      string2Card("9♥") shouldEqual Some(Card(Nine, Hearts))
      string2Card("9♦") shouldEqual Some(Card(Nine, Diamonds))
      string2Card("9♣") shouldEqual Some(Card(Nine, Clubs))
      string2Card("10♠") shouldEqual Some(Card(Ten, Spades))
      string2Card("10♥") shouldEqual Some(Card(Ten, Hearts))
      string2Card("10♦") shouldEqual Some(Card(Ten, Diamonds))
      string2Card("10♣") shouldEqual Some(Card(Ten, Clubs))
      string2Card("J♠") shouldEqual Some(Card(Jack, Spades))
      string2Card("J♥") shouldEqual Some(Card(Jack, Hearts))
      string2Card("J♦") shouldEqual Some(Card(Jack, Diamonds))
      string2Card("J♣") shouldEqual Some(Card(Jack, Clubs))
      string2Card("Q♠") shouldEqual Some(Card(Queen, Spades))
      string2Card("Q♥") shouldEqual Some(Card(Queen, Hearts))
      string2Card("Q♦") shouldEqual Some(Card(Queen, Diamonds))
      string2Card("Q♣") shouldEqual Some(Card(Queen, Clubs))
      string2Card("K♠") shouldEqual Some(Card(King, Spades))
      string2Card("K♥") shouldEqual Some(Card(King, Hearts))
      string2Card("K♦") shouldEqual Some(Card(King, Diamonds))
      string2Card("K♣") shouldEqual Some(Card(King, Clubs))
      string2Card("A♠") shouldEqual Some(Card(Ace, Spades))
      string2Card("A♥") shouldEqual Some(Card(Ace, Hearts))
      string2Card("A♦") shouldEqual Some(Card(Ace, Diamonds))
      string2Card("A♣") shouldEqual Some(Card(Ace, Clubs))
    }
  }
}
