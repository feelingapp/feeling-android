package app.getfeeling.feeling.api

import app.getfeeling.feeling.api.response.StatusResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeelingService {

    @GET("status")
    suspend fun getStatus(): Response<StatusResponse>
}
