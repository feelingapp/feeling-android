package app.getfeeling.feeling.api

import android.content.Context
import app.getfeeling.feeling.util.SecurePreferencesHelper
import app.getfeeling.feeling.valueobjects.Token
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val moshi: Moshi,
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Add basic JSON headers
        val requestBuilder = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")

        // Add access token if available
        getTokenModel()?.run {
            requestBuilder.addHeader("Authorization", "Bearer ${this.accessToken}")
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun getTokenModel(): Token? {
        val jsonAdapter = moshi.adapter(Token::class.java)
        val json = SecurePreferencesHelper.getValue(context, "TOKEN")

        return if (json != null) jsonAdapter.fromJson(json) else null
    }
}
