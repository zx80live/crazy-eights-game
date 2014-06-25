package com.zx80live.examples.crazyeights.cards.dsl

import com.zx80live.examples.crazyeights.cards.Card
import com.zx80live.examples.crazyeights.cards.rules.crazy8.Crazy8DiscardsValidator
import org.scalatest.{Matchers, WordSpec}


/**
 *
 * @author Andrew Proshkin
 */
class CardsDSLSpec extends WordSpec with Matchers {


  "test" in {
    import com.zx80live.examples.crazyeights.cards.dsl.CardsDSL._


    implicit val validator = new Crazy8DiscardsValidator with MoveValidator {
      override def validateMove(discard: List[Card], cards: List[Card]): Boolean = {
        validateDiscard(cards.head, discard)
      }
    }


    println("5♦,5♣,5♠,5♥" --> "2♦,2♣,2♠,2♥")
  }
}
