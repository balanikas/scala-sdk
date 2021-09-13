package com.optimizely.ab

import java.lang.Thread.sleep
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Random


class NoopEventHandler extends EventHandler {

  override def dispatchEvent(logEvent: String): Future[String] = Future {
    sleep(Random.nextInt(1000))
    logEvent
  }
}
