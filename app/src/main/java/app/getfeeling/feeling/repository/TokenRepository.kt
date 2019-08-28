package app.getfeeling.feeling.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.liveData
import app.getfeeling.feeling.api.FeelingService
import app.getfeeling.feeling.api.models.ErrorsModel
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.util.SecurePreferencesHelper
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
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
    override fun exchangeCodeForToken(getTokenModel: GetTokenModel) = liveData(Dispatchers.IO) {
        val response = feelingService.getToken(getTokenModel)

        if (response.isSuccessful && response.body() != null) {
            emit(response.body() as TokenModel)
        }
    }

    override fun saveToken(tokenModel: TokenModel) {
        val jsonAdapter = moshi.adapter(TokenModel::class.java)
        val json = jsonAdapter.toJson(tokenModel)
        SecurePreferencesHelper.setValue(context, "KEY", json)
    }

    override fun getToken(): TokenModel? {
        val jsonAdapter = moshi.adapter(TokenModel::class.java)
        val json = SecurePreferencesHelper.getValue(context, "KEY")

        return if (json != null) jsonAdapter.fromJson(json) else null
    }
}

