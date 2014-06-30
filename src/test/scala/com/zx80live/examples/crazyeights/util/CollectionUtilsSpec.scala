package com.zx80live.examples.crazyeights.util

import com.zx80live.examples.crazyeights.util.CollectionUtils._
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CollectionUtilsSpec extends WordSpec with Matchers {

  "maxList" in {
    maxList(None, None) shouldEqual None
    maxList(None, Some(List())) shouldEqual None
    maxList(Some(List()), None) shouldEqual None
    maxList(Some(List()), Some(List())) shouldEqual None

    maxList(Some(List(1, 2, 3)), None) shouldEqual Some(List(1, 2, 3))
    maxList(None, Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3))
    maxList(Some(List(1, 2, 3)), Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3))

    maxList(Some(List(1, 2, 3)), Some(List(1, 2, 3, 4, 5))) shouldEqual Some(List(1, 2, 3, 4, 5))
    maxList(Some(List(1, 2, 3, 4, 5)), Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3, 4, 5))

  }
}
