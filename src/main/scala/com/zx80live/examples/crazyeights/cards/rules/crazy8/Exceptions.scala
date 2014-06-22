package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 *
 * @author Andrew Proshkin
 */
object Exceptions {

  class IllegalCardFormatException(msg: String = "") extends RuntimeException

  class DiscardException(msg: String = "") extends RuntimeException

  class EmptyCardsDiscardException extends DiscardException("can't discard empty cards list")

}
