package com.zx80live.examples.crazyeights

import com.zx80live.examples.crazyeights.cards.Rank

/**
 *
 * @author Andrew Proshkin
 */
object test extends App {

  import com.zx80live.examples.crazyeights.cards.dsl.ConversionUtils._



  println(card"${Rank.Ace}D")
}
