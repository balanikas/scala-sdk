package com.optimizely.ab


case class ConversionEventBuilder(userContext:UserContext, eventId: String = "", eventKey: String = "") {

  def withEventId(eventId:String): ConversionEventBuilder = copy(eventId = eventId)
  def withEventKey(eventKey:String): ConversionEventBuilder = copy(eventKey = eventKey)

  def build: ConversionEvent = new ConversionEvent(userContext, eventId, eventKey)
}
