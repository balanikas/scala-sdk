package com.optimizely.ab

import scala.concurrent.Future

trait EventProcessor {
  def process(userEvent: ConversionEvent): Future[String]
}
