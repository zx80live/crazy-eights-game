package com.zx80live.examples.crazyeights.util

import scala.collection.immutable.List

/**
 *
 * @author Andrew Proshkin
 */
class CircularList[T](xs: List[T] = Nil) {
  var list = xs
  var pos = 0

  def next = {
    if (pos == list.length)
      pos = 0
    val e = list(pos)
    pos = pos + 1
    e
  }

  //TODO test
  def length = list.length

  def add(e: T): CircularList[T] = {
    list = list :+ e
    this
  }

  //TODO test
  def insert(e: T): CircularList[T] = {
    list = e :: list
    this
  }

  override def toString = list.toString()

}

object CircularList {
  def apply[T](list: List[T]) = new CircularList[T](list)
}
