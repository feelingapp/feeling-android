package app.getfeeling.feeling.api

import app.getfeeling.feeling.valueobjects.*
import retrofit2.Response
import retrofit2.http.*

interface FeelingService {

    @GET("status")
    suspend fun getStatus(): Response<Unit>

    // Authorization endpoints
    @POST("token")
    suspend fun getToken(@Body tokenRequest: TokenRequest): Response<Token>

    // User endpoints
    @GET("user/exists")
    suspend fun checkIfAccountExists(@Query("email") email: String): Response<UserExists>

    @GET("user/me")
    suspend fun getMe(): Response<User>

    // Feeling endpoints
    @GET("feeling")
    suspend fun getFeelings(): Response<Feelings>

    @GET("feeling/:{id}")
    suspend fun getFeeling(@Path("id") id: Int): Response<Feeling>

    @POST("feeling")
    suspend fun createFeeling(@Body feeling: Feeling): Response<Unit>

    @PUT("feeling/:{id}")
    suspend fun updateFeeling(@Path("id") id: Int, @Body feeling: Feeling): Response<Unit>

    @DELETE("feeling/:{id}")
    suspend fun deleteFeeling(@Path("id") id: Int): Response<Unit>

    // Quote endpoints
    @GET("quote/:{emotion}")
    suspend fun getQuote(@Path("emotion") emotion: String): Response<Quote>

    // Settings endpoints
    @GET("settings")
    suspend fun getSettings(): Response<Settings>

    @POST("settings")
    suspend fun updateSettings(@Body settings: Settings): Response<Unit>
}

