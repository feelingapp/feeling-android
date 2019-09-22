package app.getfeeling.feeling.valueobjects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feelings(
    val feelings: List<Feeling>
)
