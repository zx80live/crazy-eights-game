package com.zx80live.examples.crazyeights.util

/**
 *
 * @author Andrew Proshkin
 */
object CollectionUtils {

  /**
   * Returns longest collection into option
   *
   * @param opt1 - first collection
   * @param opt2 - second collection
   * @tparam T - type of collection
   * @return
   */
  def maxList[T](opt1: Option[List[T]], opt2: Option[List[T]]): Option[List[T]] = {
    //TODO refactoring: apply some functional pattern
    List(opt1, opt2).flatten match {
      case List(Nil, Nil) => None
      case List(Nil, b) => Some(b)
      case List(a, Nil) => Some(a)
      case List(a, b) if a.length >= b.length => Some(a)
      case List(a, b) if a.length < b.length => Some(b)
      case List(Nil) => None
      case List(a) => Some(a)
      case _ => None
    }
  }
}
