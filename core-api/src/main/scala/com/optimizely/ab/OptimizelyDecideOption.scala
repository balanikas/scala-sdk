package com.optimizely.ab

object OptimizelyDecideOption extends Enumeration {
  type OptimizelyDecideOption = Value
  val DISABLE_DECISION_EVENT,
  ENABLED_FLAGS_ONLY,
  IGNORE_USER_PROFILE_SERVICE,
  INCLUDE_REASONS,
  EXCLUDE_VARIABLES = Value
}
