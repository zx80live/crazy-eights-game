package com.zx80live.examples.crazyeights.cards.dsl

import com.zx80live.examples.crazyeights.cards.dsl.CardsDSL._
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8GameContext._
import org.scalatest.{Matchers, WordSpec}


/**
 *
 * @author Andrew Proshkin
 */
class CardsDSLSpec extends WordSpec with Matchers {


  "test" in {
    println("5♦,5♣,5♠,5♥" --> "2♦,2♣,2♠,2♥")
  }
}
