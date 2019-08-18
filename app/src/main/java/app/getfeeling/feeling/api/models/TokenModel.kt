package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json

data class TokenModel(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "expires_in") val expiresIn: String,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "refresh_token") val refreshToken: String
)

