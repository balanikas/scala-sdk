package com.optimizely.ab

object ExperimentStatus extends Enumeration {
  type ExperimentStatus = Value
  val Running, Launched, Paused, Not_Started, Archived = Value
}

case class Experiment(
                       id: String,
                       key: String,
                       status: String,
                       //                       layerId:String,
                       //                       audienceIds: List[String],
                       //                       audienceConditions: AnyRef,
                       variations: List[Variation])
{

  def isActive: Boolean = {
    val parsedStatus = ExperimentStatus.withName(status)
    parsedStatus.equals(ExperimentStatus.Running) || parsedStatus.equals(ExperimentStatus.Launched)
  }
}