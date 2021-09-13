package com.optimizely.ab

case class FeatureVariable(
                            id: String,
                            key: String,
                            defaultValue: String,
                            status: VariableStatus,
                            tipe: String,
                            subType: String) {
  val STRING_TYPE = "string"
  val INTEGER_TYPE = "integer"
  val DOUBLE_TYPE = "double"
  val BOOLEAN_TYPE = "boolean"
  val JSON_TYPE = "json"
}


