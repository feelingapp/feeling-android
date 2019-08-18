package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json

data class GetTokenModel(
    @Json(name = "code") val code: String,
    @Json(name = "code_verifier") val codeVerifier: String,
    @Json(name = "client_id") val clientId: String
)
