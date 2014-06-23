package com.zx80live.examples.crazyeights.util

/**
 *
 * @author Andrew Proshkin
 */
trait PrettyListView {
  def prettyList(list: List[_]): String = {
    list.map(_.toString.trim).mkString(",")
  }
}
