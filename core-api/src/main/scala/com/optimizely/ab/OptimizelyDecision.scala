package com.optimizely.ab

case class OptimizelyDecision(
                               variationKey: String,
                               enabled: Boolean,
                               variables: AnyRef,
                               ruleKey: String,
                               flagKey: String,
                               userContext: OptimizelyUserContext) {

}
