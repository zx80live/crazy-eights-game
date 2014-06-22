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
  def maxSeq[T](opt1: Option[List[T]], opt2: Option[List[T]]): Option[List[T]] = {
    //TODO refactoring: apply some functional pattern
    Seq(opt1, opt2).flatten match {
      case Seq(Nil, Nil) => None
      case Seq(Nil, b) => Some(b)
      case Seq(a, Nil) => Some(a)
      case Seq(a, b) if a.length >= b.length => Some(a)
      case Seq(a, b) if a.length < b.length => Some(b)
      case Seq(Nil) => None
      case Seq(a) => Some(a)
      case _ => None
    }
  }
}
