package com.zx80live.examples.crazyeights.model

import com.zx80live.examples.crazyeights.SeqUtils._
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class SeqUtilsSpec extends WordSpec with Matchers {

  "maxSeq" in {
    maxSeq(None, None) shouldEqual None
    maxSeq(None, Some(List())) shouldEqual None
    maxSeq(Some(List()), None) shouldEqual None
    maxSeq(Some(List()), Some(List())) shouldEqual None

    maxSeq(Some(List(1, 2, 3)), None) shouldEqual Some(List(1, 2, 3))
    maxSeq(None, Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3))
    maxSeq(Some(List(1, 2, 3)), Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3))

    maxSeq(Some(List(1, 2, 3)), Some(List(1, 2, 3, 4, 5))) shouldEqual Some(List(1, 2, 3, 4, 5))
    maxSeq(Some(List(1, 2, 3, 4, 5)), Some(List(1, 2, 3))) shouldEqual Some(List(1, 2, 3, 4, 5))

  }
}
