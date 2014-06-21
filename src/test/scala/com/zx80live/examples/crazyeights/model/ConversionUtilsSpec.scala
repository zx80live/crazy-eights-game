package com.zx80live.examples.crazyeights.model

import org.scalatest._

/**
 *
 * @author Andrew Proshkin
 */
class ConversionUtilsSpec extends FlatSpec with Matchers {

  import com.zx80live.examples.crazyeights.model.ConversionUtils._
  import com.zx80live.examples.crazyeights.model.Rank._

  "string2Rank" should "converts correct string values into Some(Rank.Value)" in {
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

  "string2Rank" should "converts wrong string values into None" in {
    string2Rank("") shouldEqual None
    string2Rank(" ") shouldEqual None
    string2Rank("1") shouldEqual None
    string2Rank("11") shouldEqual None
    string2Rank("ABC") shouldEqual None
    string2Rank("A B C ") shouldEqual None
    string2Rank(" 1 2 3 4  5") shouldEqual None
  }
}
