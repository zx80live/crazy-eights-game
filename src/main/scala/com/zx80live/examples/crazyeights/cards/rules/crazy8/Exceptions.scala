package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 *
 * @author Andrew Proshkin
 */
object Exceptions {

  class DiscardException(msg: String) extends IllegalArgumentException

  class EmptyCardsDiscardException extends DiscardException("can't discard empty cards list")

}
