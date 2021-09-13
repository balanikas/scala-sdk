package com.optimizely.ab

import scala.util.Random

class Bucketer {
  def getVariation(experiment: Experiment, bucketingId: String, projectConfig: ProjectConfig): Variation = {
    val random = new Random
    experiment.variations(random.nextInt(experiment.variations.length))
  }
}
