package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountModel(
    @Json(name = "id") val id: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "email") val email: String,
    @Json(name = "verified") val verified: Boolean
)
