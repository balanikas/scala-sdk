package com.optimizely.ab

case class UserContext(userId: String, projectConfig: ProjectConfig, attributes: Map[String, AnyRef])
