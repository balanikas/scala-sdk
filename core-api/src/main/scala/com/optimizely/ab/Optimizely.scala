package com.optimizely.ab

import scala.concurrent.ExecutionContext.Implicits.global

class Optimizely(projectConfigManager: ProjectConfigManager, decisionService: DecisionService, eventProcessor: EventProcessor) {

  def isValid: Boolean =
    projectConfigManager.getConfig != null

  def getProjectConfig: ProjectConfig = {
    projectConfigManager.getConfig
  }

  def decide(user: OptimizelyUserContext, key: String): OptimizelyDecision = {
    val projectConfig = projectConfigManager.getConfig

    projectConfig.getFeatureFlag(key) match {
      case Some(flag) =>
        val featureDecision = decisionService.getVariationForFeature(flag, user.userId, user.attributes, projectConfig)
        OptimizelyDecision(featureDecision.variation.key, true, null, featureDecision.experiment.key, flag.key, user)
      case None => sys exit 1
    }
  }

  def track(eventName: String, userId: String, attributes: Map[String, AnyRef], eventTags: Map[String, _]): Unit = {
    if(validateUserId(userId)){
      return
    }

    if (eventName == null || eventName.trim.isEmpty) {
      return
    }

    val userCtx = UserContext(userId, getProjectConfig, attributes)
    val conversionEvent = ConversionEvent
      .builder(userCtx)
      .withEventId("eventId")
      .withEventKey("eventKey")
      .build

    val aaa = conversionEvent.getTimestamp
    val bbb = conversionEvent.getUUID

    val result = eventProcessor.process(conversionEvent)
    result onSuccess {
      case result => println(s"tracked event: $result")
    }
    result onFailure {
      case t => println(s"Event nor tracked: ${t.getMessage}")
    }
  }

  private def validateUserId(userId: String): Boolean =
    userId == null
}


