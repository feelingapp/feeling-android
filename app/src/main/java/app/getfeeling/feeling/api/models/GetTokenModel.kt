package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

enum class GrantType {
    @Json(name = "authorization_code")
    AUTHORIZATION_CODE,

    @Json(name = "refresh_token")
    REFRESH_TOKEN
}

@JsonClass(generateAdapter = true)
data class GetTokenModel(
    @Json(name = "grant_type") val grantType: GrantType,
    @Json(name = "code") val code: String,
    @Json(name = "code_verifier") val codeVerifier: String,
    @Json(name = "client_id") val clientId: String
)
