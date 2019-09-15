package app.getfeeling.feeling.api

import app.getfeeling.feeling.api.models.*
import retrofit2.Response
import retrofit2.http.*

interface FeelingService {

    @GET("status")
    suspend fun getStatus(): Response<Unit>

    // Authorization endpoints
    @POST("token")
    suspend fun getToken(@Body getTokenModel: GetTokenModel): Response<TokenModel>

    // Account endpoints
    @GET("account/exists")
    suspend fun checkIfAccountExists(@Query("email") email: String): Response<AccountExistsModel>

    // Feeling endpoints
    @GET("feeling")
    suspend fun getFeelings(): Response<FeelingsModel>

    @GET("feeling/:{id}")
    suspend fun getFeeling(@Path("id") id: Int): Response<FeelingModel>

    @POST("feeling")
    suspend fun createFeeling(@Body feelingModel: FeelingModel): Response<Unit>

    @PUT("feeling/:{id}")
    suspend fun updateFeeling(@Path("id") id: Int, @Body feelingModel: FeelingModel): Response<Unit>

    @DELETE("feeling/:{id}")
    suspend fun deleteFeeling(@Path("id") id: Int): Response<Unit>

    // Quote endpoints
    @GET("quote/:{emotion}")
    suspend fun getQuote(@Path("emotion") emotion: String): Response<QuoteModel>

    // Settings endpoints
    @GET("settings")
    suspend fun getSettings(): Response<SettingsModel>

    @POST("settings")
    suspend fun updateSettings(@Body settingsModel: SettingsModel): Response<Unit>
}

