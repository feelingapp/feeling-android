package app.getfeeling.feeling.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.util.SecurePreferencesHelper
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
    private val errorConverter: Converter<ResponseBody, ErrorsModel>,
    private val moshi: Moshi,
    private val context: Context
) : ITokenRepository {
    private val _tokenModel = MutableLiveData<TokenModel>()
    override val tokenModel: LiveData<TokenModel> = _tokenModel

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
        val jsonAdapter = moshi.adapter(TokenModel::class.java)
        val json = SecurePreferencesHelper.getValue(context, "TOKEN")
        _tokenModel.value = if (json != null) jsonAdapter.fromJson(json) else null
    }

    override suspend fun exchangeCodeForToken(getTokenModel: GetTokenModel) =
        withContext(Dispatchers.IO) {
            val response = feelingService.getToken(getTokenModel)

            if (response.isSuccessful && response.body() != null)
                saveToken(response.body() as TokenModel)
        }

    override suspend fun saveToken(tokenModel: TokenModel) =
        withContext(Dispatchers.IO) {
            val jsonAdapter = moshi.adapter(TokenModel::class.java)
            val json = jsonAdapter.toJson(tokenModel)
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

