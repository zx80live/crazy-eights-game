package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 * @author Andrew Proshkin
 *
 * @param param - event message
 */
case class WorkspaceEvent(param: String)

sealed class RecreateStockpileEvent extends WorkspaceEvent("stockpile is empty, recreate them from discard pile")

class DiscardEvent

object JokerDiscardEvent extends DiscardEvent

object EightDiscardEvent extends DiscardEvent

object SuccessDiscardEvent extends DiscardEvent