package app.getfeeling.feeling.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteModel(
    val quote: String,
    val author: String
)
