package com.zx80live.examples.crazyeights.cards.rules.crazy8

import com.zx80live.examples.crazyeights.cards.Suit

/**
 * @author Andrew Proshkin
 *
 * @param param - some event param
 * @tparam T - some type
 */
case class WorkspaceEvent[T](param: T)

sealed class RecreateStockpileEvent[String] extends WorkspaceEvent("stockpile is empty, recreate them from discard pile")

class DiscardEvent

sealed class JokerDiscardEvent extends DiscardEvent

sealed class EightDiscardEvent(suit: Suit.Value) extends DiscardEvent

sealed class SuccessDiscardEvent extends DiscardEvent