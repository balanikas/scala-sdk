package com.optimizely.ab

import java.util.UUID

trait UserEvent {
  private lazy val uuid = UUID.randomUUID.toString
  private lazy val timestamp = System.currentTimeMillis

  def getUUID: String = uuid
  def getTimestamp: Long = timestamp
}
