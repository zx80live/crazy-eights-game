package com.zx80live.examples.crazyeights.util

import com.zx80live.examples.crazyeights.cards.rules.Workspace

/**
 *
 * @author Andrew Proshkin
 */
trait ConsoleRenderer {
  def toString(list: List[_]): String = {
    list.map(_.toString.trim).mkString(",")
  }


  def toString(ws: Workspace): String = {
    s"""
            |  Workspace(shuffle: ${ws.isShuffle}) {
            |    stockpile:   ${toString(ws.stockPile)}
            |    discardPile: ${toString(ws.discardPile)}
            |    currentCard: ${ws.currentCard.toString.trim}
            |  }
          """.stripMargin
  }

}
