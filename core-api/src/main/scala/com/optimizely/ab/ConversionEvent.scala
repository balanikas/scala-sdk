package com.optimizely.ab


case class ConversionEvent(userContext:UserContext, eventId: String, eventKey: String) extends UserEvent

object ConversionEvent {
  def builder(userContext: UserContext): ConversionEventBuilder = ConversionEventBuilder(userContext)
}
