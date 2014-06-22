package com.zx80live.examples.crazyeights.cards

import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class CardsHelperSpec extends WordSpec with Matchers with CardsHelper {
  "get lazy 52-cards deck" in {
    deck52.length shouldEqual 52
  }

  "get lazy 54-cards deck" in {
    deck54.length shouldEqual 54
  }
}
