package com.optimizely.ab


sealed trait VariableStatus {
  def name: String
}

case object Active extends VariableStatus {
  val name = "active"
}

case object Archived extends VariableStatus {
  val name = "archived"
}
