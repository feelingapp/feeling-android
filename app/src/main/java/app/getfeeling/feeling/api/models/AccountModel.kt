package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountModel(
    val exists: Boolean,
    @Json(name = "first_name") val firstName: String?
)
