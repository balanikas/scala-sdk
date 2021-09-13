package com.optimizely.ab

case class OptimizelyUserContext(userId: String, optimizely: Optimizely, attributes: Map[String, AnyRef]) {

  def setAttribute(key: String, value: AnyRef): OptimizelyUserContext = {
    require(!("" equals key))
    val updatedAttributes = attributes + (key -> value)
    OptimizelyUserContext(userId, optimizely, updatedAttributes)
  }

  def decide(key: String): OptimizelyDecision = {
    optimizely.decide(this, key)
  }

  def trackEventAsync(eventName: String): Unit = {
    trackEvent(eventName, Map.empty)
  }

  def trackEvent(eventName: String, eventTags: Map[String, _]): Unit = {
    optimizely.track(eventName, userId, attributes, eventTags)
  }
}

object OptimizelyUserContext {

  def apply(userId: String, optimizely: Optimizely): OptimizelyUserContext = {
    OptimizelyUserContext(userId, optimizely, Map.empty[String, AnyRef])
  }
}