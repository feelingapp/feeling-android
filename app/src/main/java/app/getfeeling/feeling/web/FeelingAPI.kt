package app.getfeeling.feeling.web

import app.getfeeling.feeling.room.entities.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FeelingAPI {
    @GET("user")
    fun getUserAsync(@Path("id") id: Int): Deferred<Response<User>>
}
