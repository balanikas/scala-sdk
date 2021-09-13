package com.optimizely.ab

class DecisionService(bucketer: Bucketer) {

  def getVariation(experiment: Experiment, userId: String, projectConfig: ProjectConfig): Option[Variation] = {
    if (experiment.isActive) {
      val v = bucketer.getVariation(experiment, userId, projectConfig)
      Some(v)
    } else None
  }

  def getVariationForFeature(
                              flag: FeatureFlag,
                              userId: String,
                              attributes: Map[String, AnyRef],
                              projectConfig: ProjectConfig): FeatureDecision = {

    if (flag.experimentIds.nonEmpty) {
      for (expId <- flag.experimentIds) {
        projectConfig.getExperiment(expId) match {
          case Some(experiment) =>
            val variation = getVariation(experiment, userId, projectConfig)
            return FeatureDecision(experiment,variation.get)
          case None => sys exit 1
        }
      }
    }

    sys exit 1
  }
}
