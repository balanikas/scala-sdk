package com.optimizely.ab


//import org.json4s.{DefaultFormats, Formats, JString}
import org.json4s.native.JsonMethods._
import org.json4s._

import org.json4s._

case class ProjectConfig(raw: String) {
  implicit val formats: Formats = DefaultFormats

  def getExperiment(id: String): Option[Experiment] = {
    val json = parse(raw)
    val experiments = (json \ "experiments").extract[List[Experiment]]
    experiments find (_.id == id)
  }

  def getFeatureFlag(key: String): Option[FeatureFlag] = {
    val json = parse(raw)
    val flags = (json \\ "featureFlags").extract[List[FeatureFlag]]
    flags find (_.key == key)
  }

  def getProjectId: Long = {
    val json = parse(raw)
    val projId = json \\ "projectId"

    projId match {
      case JString(s) =>
        s.toLong
      case _ => 0
    }
  }
}
