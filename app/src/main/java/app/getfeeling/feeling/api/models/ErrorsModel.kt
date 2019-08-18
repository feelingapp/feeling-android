package app.getfeeling.feeling.api.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorsModel(val errors: List<Error>) {
    data class Error(
        val type: String,
        val message: String
    )
}
