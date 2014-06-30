package com.zx80live.examples.crazyeights.util

import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CircularListSpec extends WordSpec with Matchers {

  "test length" in {
    val list = new CircularList[Int]()
    list.length shouldEqual 0
    list.add(1)
    list.add(2)
    list.add(3)
    list.length shouldEqual 3
    list.insert(4)
    list.length shouldEqual 4
    list.insert(5)
    list.length shouldEqual 5
  }

  "test next" in {
    val list = CircularList(List(1,2,3,4,5))
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

    list.add(6).add(7)
    println(list.toString)
    list.next shouldEqual 2
    list.next shouldEqual 3
    list.next shouldEqual 4
    list.next shouldEqual 5
    list.next shouldEqual 6
    list.next shouldEqual 7
    list.next shouldEqual 1
  }

  "test insert" in {
    val list = new CircularList[Int]()
    list.insert(1)
    list.insert(2)
    list.insert(3)
    list.insert(4)
    list.insert(5)

    list.next shouldEqual 5
    list.next shouldEqual 4
    list.next shouldEqual 3
    list.next shouldEqual 2
    list.next shouldEqual 1

  }

}
