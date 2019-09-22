package app.getfeeling.feeling.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.valueobjects.Errors
import app.getfeeling.feeling.valueobjects.TokenRequest
import app.getfeeling.feeling.valueobjects.Token
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.util.SecurePreferencesHelper
import com.auth0.android.jwt.JWT
import com.squareup.moshi.Moshi
import de.adorsys.android.securestoragelibrary.SecurePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import javax.inject.Inject
import javax.inject.Singleton

@WorkerThread
@Singleton
class TokenRepository @Inject constructor(
    private val feelingService: FeelingService,
    private val errorConverter: Converter<ResponseBody, Errors>,
    private val moshi: Moshi,
    private val context: Context
) : ITokenRepository {
    private val _tokenModel = MutableLiveData<Token>()
    override val token: LiveData<Token> = _tokenModel

    override fun getUserId(): String? {
        return _tokenModel.value?.run {
            val jwt = JWT(this.accessToken)
            jwt.getClaim("sub").asString()
        }
    }

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "TOKEN0") {
            getTokenFromStorage()
        }
    }

    init {
        SecurePreferences.registerOnSharedPreferenceChangeListener(context, listener)
        getTokenFromStorage()
    }

    private fun getTokenFromStorage() = run {
        val jsonAdapter = moshi.adapter(Token::class.java)
        val json = SecurePreferencesHelper.getValue(context, "TOKEN")
        _tokenModel.value = if (json != null) jsonAdapter.fromJson(json) else null
    }

    override suspend fun exchangeCodeForToken(tokenRequest: TokenRequest) =
        withContext(Dispatchers.IO) {
            val response = feelingService.getToken(tokenRequest)

            if (response.isSuccessful && response.body() != null)
                saveToken(response.body() as Token)
        }

    override suspend fun saveToken(token: Token) =
        withContext(Dispatchers.IO) {
            val jsonAdapter = moshi.adapter(Token::class.java)
            val json = jsonAdapter.toJson(token)
            SecurePreferencesHelper.setValue(context, "TOKEN", json)

        }

    override suspend fun clearToken() =
        withContext(Dispatchers.IO) {
            SecurePreferencesHelper.removeValue(context, "TOKEN")
        }

    override fun hasValidToken(): Boolean {
        return _tokenModel.value != null
    }
}
