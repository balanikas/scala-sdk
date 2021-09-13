package com.optimizely.ab

import scala.concurrent.Future


trait EventHandler {
  def dispatchEvent (logEvent: String) : Future[String]
}

