package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json

data class AccountModel(
    val exists: Boolean,
    @Json(name = "first_name") val firstName: String?
)
