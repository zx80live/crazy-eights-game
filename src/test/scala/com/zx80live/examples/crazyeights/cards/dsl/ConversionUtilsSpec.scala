package com.zx80live.examples.crazyeights.cards.dsl

import com.zx80live.examples.crazyeights.cards.CardsHelper._
import com.zx80live.examples.crazyeights.cards.Rank._
import com.zx80live.examples.crazyeights.cards.Suit._
import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._
import com.zx80live.examples.crazyeights.cards.{Card, Rank, Suit}
import org.scalatest._

/**
 * Specification for ConversionUtils
 *
 * @author Andrew Proshkin
 */
class ConversionUtilsSpec extends WordSpec with Matchers {

  "string2Rank" when {
    "converts correct rankStrings into Some(_)" in {
      rankStrings foreach (r => assert(string2Rank(r) != None))
    }

    "converts correct rankStrings into Some(_) implicitly" in {
      rankStrings foreach (r => assert((r: Option[Rank.Value]) != None))
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

    "converts correct suitStrings into Some(_) implicitly" in {
      suitStrings foreach (r => assert((r: Option[Suit.Value]) != None))
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
    "converts correct deck54Strings into Some(Card)" in {
      deck54Strings foreach (c => assert(string2Card(c) != None))
    }

    "converts correct deck54Strings into Some(Card) implicitly" in {
      deck54Strings foreach (c => assert((c: Option[Card]) != None))
    }

    "converts correct string values into Some(Card)" in {
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

    "converts correct string values into Some(Card) implicitly" in {
      ("★": Option[Card]) shouldEqual Some(Card(BlackJoker))
      ("☆": Option[Card]) shouldEqual Some(Card(WhiteJoker))
      ("2♠": Option[Card]) shouldEqual Some(Card(Two, Spades))
      ("2♥": Option[Card]) shouldEqual Some(Card(Two, Hearts))
      ("2♦": Option[Card]) shouldEqual Some(Card(Two, Diamonds))
      ("2♣": Option[Card]) shouldEqual Some(Card(Two, Clubs))
      ("3♠": Option[Card]) shouldEqual Some(Card(Three, Spades))
      ("3♥": Option[Card]) shouldEqual Some(Card(Three, Hearts))
      ("3♦": Option[Card]) shouldEqual Some(Card(Three, Diamonds))
      ("3♣": Option[Card]) shouldEqual Some(Card(Three, Clubs))
      ("4♠": Option[Card]) shouldEqual Some(Card(Four, Spades))
      ("4♥": Option[Card]) shouldEqual Some(Card(Four, Hearts))
      ("4♦": Option[Card]) shouldEqual Some(Card(Four, Diamonds))
      ("4♣": Option[Card]) shouldEqual Some(Card(Four, Clubs))
      ("5♠": Option[Card]) shouldEqual Some(Card(Five, Spades))
      ("5♥": Option[Card]) shouldEqual Some(Card(Five, Hearts))
      ("5♦": Option[Card]) shouldEqual Some(Card(Five, Diamonds))
      ("5♣": Option[Card]) shouldEqual Some(Card(Five, Clubs))
      ("6♠": Option[Card]) shouldEqual Some(Card(Six, Spades))
      ("6♥": Option[Card]) shouldEqual Some(Card(Six, Hearts))
      ("6♦": Option[Card]) shouldEqual Some(Card(Six, Diamonds))
      ("6♣": Option[Card]) shouldEqual Some(Card(Six, Clubs))
      ("7♠": Option[Card]) shouldEqual Some(Card(Seven, Spades))
      ("7♥": Option[Card]) shouldEqual Some(Card(Seven, Hearts))
      ("7♦": Option[Card]) shouldEqual Some(Card(Seven, Diamonds))
      ("7♣": Option[Card]) shouldEqual Some(Card(Seven, Clubs))
      ("8♠": Option[Card]) shouldEqual Some(Card(Eight, Spades))
      ("8♥": Option[Card]) shouldEqual Some(Card(Eight, Hearts))
      ("8♦": Option[Card]) shouldEqual Some(Card(Eight, Diamonds))
      ("8♣": Option[Card]) shouldEqual Some(Card(Eight, Clubs))
      ("9♠": Option[Card]) shouldEqual Some(Card(Nine, Spades))
      ("9♥": Option[Card]) shouldEqual Some(Card(Nine, Hearts))
      ("9♦": Option[Card]) shouldEqual Some(Card(Nine, Diamonds))
      ("9♣": Option[Card]) shouldEqual Some(Card(Nine, Clubs))
      ("10♠": Option[Card]) shouldEqual Some(Card(Ten, Spades))
      ("10♥": Option[Card]) shouldEqual Some(Card(Ten, Hearts))
      ("10♦": Option[Card]) shouldEqual Some(Card(Ten, Diamonds))
      ("10♣": Option[Card]) shouldEqual Some(Card(Ten, Clubs))
      ("J♠": Option[Card]) shouldEqual Some(Card(Jack, Spades))
      ("J♥": Option[Card]) shouldEqual Some(Card(Jack, Hearts))
      ("J♦": Option[Card]) shouldEqual Some(Card(Jack, Diamonds))
      ("J♣": Option[Card]) shouldEqual Some(Card(Jack, Clubs))
      ("Q♠": Option[Card]) shouldEqual Some(Card(Queen, Spades))
      ("Q♥": Option[Card]) shouldEqual Some(Card(Queen, Hearts))
      ("Q♦": Option[Card]) shouldEqual Some(Card(Queen, Diamonds))
      ("Q♣": Option[Card]) shouldEqual Some(Card(Queen, Clubs))
      ("K♠": Option[Card]) shouldEqual Some(Card(King, Spades))
      ("K♥": Option[Card]) shouldEqual Some(Card(King, Hearts))
      ("K♦": Option[Card]) shouldEqual Some(Card(King, Diamonds))
      ("K♣": Option[Card]) shouldEqual Some(Card(King, Clubs))
      ("A♠": Option[Card]) shouldEqual Some(Card(Ace, Spades))
      ("A♥": Option[Card]) shouldEqual Some(Card(Ace, Hearts))
      ("A♦": Option[Card]) shouldEqual Some(Card(Ace, Diamonds))
      ("A♣": Option[Card]) shouldEqual Some(Card(Ace, Clubs))
    }

    "card string interpolation into Some(Card)" in {
      card"★" shouldEqual Some(Card(BlackJoker))
      card"☆" shouldEqual Some(Card(WhiteJoker))
      card"2♠" shouldEqual Some(Card(Two, Spades))
      card"2♥" shouldEqual Some(Card(Two, Hearts))
      card"2♦" shouldEqual Some(Card(Two, Diamonds))
      card"2♣" shouldEqual Some(Card(Two, Clubs))
      card"3♠" shouldEqual Some(Card(Three, Spades))
      card"3♥" shouldEqual Some(Card(Three, Hearts))
      card"3♦" shouldEqual Some(Card(Three, Diamonds))
      card"3♣" shouldEqual Some(Card(Three, Clubs))
      card"4♠" shouldEqual Some(Card(Four, Spades))
      card"4♥" shouldEqual Some(Card(Four, Hearts))
      card"4♦" shouldEqual Some(Card(Four, Diamonds))
      card"4♣" shouldEqual Some(Card(Four, Clubs))
      card"5♠" shouldEqual Some(Card(Five, Spades))
      card"5♥" shouldEqual Some(Card(Five, Hearts))
      card"5♦" shouldEqual Some(Card(Five, Diamonds))
      card"5♣" shouldEqual Some(Card(Five, Clubs))
      card"6♠" shouldEqual Some(Card(Six, Spades))
      card"6♥" shouldEqual Some(Card(Six, Hearts))
      card"6♦" shouldEqual Some(Card(Six, Diamonds))
      card"6♣" shouldEqual Some(Card(Six, Clubs))
      card"7♠" shouldEqual Some(Card(Seven, Spades))
      card"7♥" shouldEqual Some(Card(Seven, Hearts))
      card"7♦" shouldEqual Some(Card(Seven, Diamonds))
      card"7♣" shouldEqual Some(Card(Seven, Clubs))
      card"8♠" shouldEqual Some(Card(Eight, Spades))
      card"8♥" shouldEqual Some(Card(Eight, Hearts))
      card"8♦" shouldEqual Some(Card(Eight, Diamonds))
      card"8♣" shouldEqual Some(Card(Eight, Clubs))
      card"9♠" shouldEqual Some(Card(Nine, Spades))
      card"9♥" shouldEqual Some(Card(Nine, Hearts))
      card"9♦" shouldEqual Some(Card(Nine, Diamonds))
      card"9♣" shouldEqual Some(Card(Nine, Clubs))
      card"10♠" shouldEqual Some(Card(Ten, Spades))
      card"10♥" shouldEqual Some(Card(Ten, Hearts))
      card"10♦" shouldEqual Some(Card(Ten, Diamonds))
      card"10♣" shouldEqual Some(Card(Ten, Clubs))
      card"J♠" shouldEqual Some(Card(Jack, Spades))
      card"J♥" shouldEqual Some(Card(Jack, Hearts))
      card"J♦" shouldEqual Some(Card(Jack, Diamonds))
      card"J♣" shouldEqual Some(Card(Jack, Clubs))
      card"Q♠" shouldEqual Some(Card(Queen, Spades))
      card"Q♥" shouldEqual Some(Card(Queen, Hearts))
      card"Q♦" shouldEqual Some(Card(Queen, Diamonds))
      card"Q♣" shouldEqual Some(Card(Queen, Clubs))
      card"K♠" shouldEqual Some(Card(King, Spades))
      card"K♥" shouldEqual Some(Card(King, Hearts))
      card"K♦" shouldEqual Some(Card(King, Diamonds))
      card"K♣" shouldEqual Some(Card(King, Clubs))
      card"A♠" shouldEqual Some(Card(Ace, Spades))
      card"A♥" shouldEqual Some(Card(Ace, Hearts))
      card"A♦" shouldEqual Some(Card(Ace, Diamonds))
      card"A♣" shouldEqual Some(Card(Ace, Clubs))
    }

    "card wrong string interpolation into None" in {
      card"" shouldEqual None
      card" " shouldEqual None
      card"123" shouldEqual None
      card"1 2 3" shouldEqual None
      card"♥" shouldEqual None
      card"2" shouldEqual None
      card"22" shouldEqual None
      card"♥♥" shouldEqual None
      card"♥ ♥" shouldEqual None
    }

    "converts wrong card strings into None" in {
      string2Card("") shouldEqual None
      string2Card(" ") shouldEqual None
      string2Card("123") shouldEqual None
      string2Card("1 2 3") shouldEqual None
      string2Card("♥") shouldEqual None
      string2Card("2") shouldEqual None
      string2Card("22") shouldEqual None
      string2Card("♥♥") shouldEqual None
      string2Card("♥ ♥") shouldEqual None
    }

    "converts wrong card strings into None implicitly" in {
      ("": Option[Card]) shouldEqual None
      (" ": Option[Card]) shouldEqual None
      ("123": Option[Card]) shouldEqual None
      ("1 2 3": Option[Card]) shouldEqual None
      ("♥": Option[Card]) shouldEqual None
      ("2": Option[Card]) shouldEqual None
      ("22": Option[Card]) shouldEqual None
      ("♥♥": Option[Card]) shouldEqual None
      ("♥ ♥": Option[Card]) shouldEqual None
    }
  }

  "string2CardsList" when {
    "converts correct cards into Some(List[Card]) with filtered wrong cards elements" in {
      string2CardsList("2♠, 3♥, K♣, Q♥, ☆") should equal(Some(List(Card(Two, Spades), Card(Three, Hearts), Card(King, Clubs), Card(Queen, Hearts), Card(WhiteJoker))))
      string2CardsList("2♠, U5, C, Q♥, ☆") should equal(Some(List(Card(Two, Spades), Card(Queen, Hearts), Card(WhiteJoker))))
      string2CardsList("11♠, B♥, 0♣, A♦") should equal(Some(List(Card(Ace, Diamonds))))
      string2CardsList("11♠, B♥, 0♣, ☆♦") should equal(None)
    }

    "cards string interpolation into Some(List[Card])" in {
      cards"2♠, 3♥, K♣, Q♥, ☆" should equal(Some(List(Card(Two, Spades), Card(Three, Hearts), Card(King, Clubs), Card(Queen, Hearts), Card(WhiteJoker))))
      cards"2♠, U5, C, Q♥, ☆" should equal(Some(List(Card(Two, Spades), Card(Queen, Hearts), Card(WhiteJoker))))
      cards"11♠, B♥, 0♣, A♦" should equal(Some(List(Card(Ace, Diamonds))))
      cards"11♠, B♥, 0♣, ☆♦" should equal(None)
      cards"UNWKNOWN" should equal(None)
    }

    "converts correct cards into Some(List[Card]) with filtered wrong cards elements implicitly" in {
      ("2♠, 3♥, K♣, Q♥, ☆": Option[List[Card]]) should equal(Some(List(Card(Two, Spades), Card(Three, Hearts), Card(King, Clubs), Card(Queen, Hearts), Card(WhiteJoker))))
      ("2♠, U5, C, Q♥, ☆": Option[List[Card]]) should equal(Some(List(Card(Two, Spades), Card(Queen, Hearts), Card(WhiteJoker))))
      ("11♠, B♥, 0♣, A♦": Option[List[Card]]) should equal(Some(List(Card(Ace, Diamonds))))
      ("11♠, B♥, 0♣, ☆♦": Option[List[Card]]) should equal(None)
    }
  }


}
