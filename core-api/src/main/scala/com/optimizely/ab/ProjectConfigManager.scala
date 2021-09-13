package com.optimizely.ab

class ProjectConfigManager(sdkKey: String) {
  private val format = "https://cdn.optimizely.com/datafiles/%s.json"

  def getConfig: ProjectConfig = {
    val raw = getRawResponse
    ProjectConfig(raw)
  }

  private def getRawResponse: String = {
    val url = String.format(format, sdkKey)
    io.Source.fromURL(url).mkString
  }
}

