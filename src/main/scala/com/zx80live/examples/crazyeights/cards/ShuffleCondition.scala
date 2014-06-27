package com.zx80live.examples.crazyeights.cards

/**
  * Condition for cards shuffle
  *
  * @author Andrew Proshkin
  */
trait ShuffleCondition {
   def acceptShuffle(deck: List[Card]): Boolean
 }
