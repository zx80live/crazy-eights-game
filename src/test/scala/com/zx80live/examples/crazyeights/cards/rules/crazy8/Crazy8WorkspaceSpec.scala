package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.CardsHelper
import org.scalatest.{Matchers, WordSpec}

/**
 *
 * @author Andrew Proshkin
 */
class Crazy8WorkspaceSpec extends WordSpec with Matchers with CardsHelper {

  "workspace" when {

    "init workspace" in {
      val ws = new Crazy8Workspace
      ws.stockPile.length shouldEqual (deck54.length - 1)
      ws.discardPile.length shouldEqual 1
      ws.currentCard shouldEqual ws.discardPile.head
      ws.stockPile.contains(ws.currentCard) shouldEqual false
    }
  }
}
