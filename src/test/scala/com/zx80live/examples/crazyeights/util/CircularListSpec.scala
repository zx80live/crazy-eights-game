package com.zx80live.examples.crazyeights.util

import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CircularListSpec extends WordSpec with Matchers {

  "test" in {
    val list = new CircularList(List(1,2,3,4,5))
    list.next shouldEqual 1
    list.next shouldEqual 2
    list.next shouldEqual 3
    list.next shouldEqual 4
    list.next shouldEqual 5
    list.next shouldEqual 1
    list.next shouldEqual 2
    list.next shouldEqual 3
    list.next shouldEqual 4
    list.next shouldEqual 5
    list.next shouldEqual 1
  }
}
