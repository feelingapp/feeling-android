package app.getfeeling.feeling.valueobjects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserExists(
    val exists: Boolean,

    @Json(name = "first_name")
    val firstName: String?
)
