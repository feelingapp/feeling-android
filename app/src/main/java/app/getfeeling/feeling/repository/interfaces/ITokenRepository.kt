package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel
import kotlinx.coroutines.Job

interface ITokenRepository {
    val tokenModel: LiveData<TokenModel>

    fun exchangeCodeForToken(getTokenModel: GetTokenModel): Job

    fun saveToken(tokenModel: TokenModel)

    fun hasValidToken(): Boolean

    fun clearToken()
}
