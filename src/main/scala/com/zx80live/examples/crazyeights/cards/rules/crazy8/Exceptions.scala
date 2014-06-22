package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 *
 * @author Andrew Proshkin
 */
object Exceptions {

  sealed class IllegalCardFormatException(msg: String = "") extends RuntimeException(msg)

  sealed class DiscardException(msg: String = "") extends RuntimeException(msg)

  sealed class EmptyCardsDiscardException extends DiscardException("can't discard empty cards list")

}
