package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 * @author Andrew Proshkin
 *
 * @param param - event message
 */
case class WorkspaceEvent(param: String = "")

sealed class RecreateStockpileEvent extends WorkspaceEvent("stockpile is empty, recreate them from discard pile")

class DealEvent extends WorkspaceEvent

class DiscardEvent

class JokerDiscardEvent extends DiscardEvent

class EightDiscardEvent extends DiscardEvent

class SuccessDiscardEvent extends DiscardEvent

class SuccessDealEvent extends DealEvent