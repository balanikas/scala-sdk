package com.optimizely.ab

case class FeatureFlag(
                        experimentIds: List[String],
                        id: String,
                        key: String,
                        rolloutId: String,
                        variables: List[FeatureVariable]
                      )
