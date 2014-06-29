package com.zx80live.examples.crazyeights.util

/**
 *
 * @author Andrew Proshkin
 */
class CircularList[T](list: List[T]) {
  var iterator = list.iterator

  def next = {
    if (!iterator.hasNext)
      iterator = list.iterator
    iterator.next
  }

  def prev = ???

  override def toString = list.toString()

}
