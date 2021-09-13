package com.optimizely.ab

import scala.concurrent.Future

class ForwardingEventProcessor(eventHandler: EventHandler) extends EventProcessor {

  override def process(userEvent: ConversionEvent): Future[String] = {
    val logEvent = s"${userEvent.eventId} for user ${userEvent.userContext.userId}"
    eventHandler.dispatchEvent(logEvent)
  }
}
