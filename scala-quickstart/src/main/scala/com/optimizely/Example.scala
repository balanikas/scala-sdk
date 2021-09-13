package com.optimizely

import com.optimizely.ab.{Bucketer, DecisionService, ForwardingEventProcessor, NoopEventHandler, Optimizely, OptimizelyUserContext, ProjectConfigManager}
import org.json4s.{DefaultFormats, Formats}
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit
import scala.util.Random

class Example {

}

object Example {

}


object ExampleApp extends App {

  implicit val formats: Formats = DefaultFormats

  val projectConfigManager = new ProjectConfigManager("***********")
  val decisionService = new DecisionService(new Bucketer)
  val eventProcessor = new ForwardingEventProcessor(new NoopEventHandler)
  val optimizely = new Optimizely(projectConfigManager, decisionService, eventProcessor)

  if(!optimizely.isValid){
    println("optimizely sdk configuration is invalid, exiting.")
    sys exit 1
  }

  val config = optimizely.getProjectConfig
  val projectId = config.getProjectId
  println(projectId)

  val unknown = config.getFeatureFlag("unknown")
  assert (unknown.isEmpty)

  val attributes = Map(
    "attr1" -> "attr1",
    "attr2" -> "attr2"
  )

  var userCtx = OptimizelyUserContext("123", optimizely, attributes)
  println(userCtx)
  userCtx = userCtx.setAttribute("attr2", "new-value")

  for(x <- userCtx.attributes) println(x)

  val featureFlag = config.getFeatureFlag("eet_feature")
  config.getFeatureFlag("eet_feature") match {
    case Some(i) =>
      println(s"feature $i found")
      for(id <- i.experimentIds){
        val experiment = config.getExperiment(id)
        println(experiment)
      }
    case None => sys exit 1
  }


  val decision = userCtx.decide("eet_feature")


  val random = new Random

  for (_ <- 0 until 50) {
    val userId = String.valueOf(random.nextInt(Integer.MAX_VALUE))
    processVisitor(userId, Map.empty[String, AnyRef])
    TimeUnit.MILLISECONDS.sleep(50)
  }
  sleep(10000)
  val break = 0

  private def processVisitor(userId: String, attributes: Map[String,AnyRef]) = {
    val projectConfigManager = new ProjectConfigManager("*********")
    val decisionService = new DecisionService(new Bucketer)
    val eventProcessor = new ForwardingEventProcessor(new NoopEventHandler)
    val optimizely = new Optimizely(projectConfigManager, decisionService, eventProcessor)

    val userCtx = OptimizelyUserContext(userId, optimizely)

    val decision = userCtx.decide("eet_feature")

    userCtx.trackEventAsync("eet_conversion")

    println(s"user $userId got ${decision.variationKey}")
  }
}
