package app.getfeeling.feeling.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelingsModel(
    val feelings: List<FeelingModel>
)
