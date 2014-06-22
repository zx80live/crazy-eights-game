package com.zx80live.examples.crazyeights.cards.rules.crazy8

/**
 * @author Andrew Proshkin
 *
 * @param param - some event param
 * @tparam T - some type
 */
case class WorkspaceEvent[T](param: T)

sealed class RecreateStockpileEvent[String] extends WorkspaceEvent("stockpile is empty, recreate them from discard pile")
