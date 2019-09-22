package app.getfeeling.feeling.valueobjects

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Errors(val errors: List<Error>) {
    data class Error(
        val type: String,
        val message: String
    )
}
