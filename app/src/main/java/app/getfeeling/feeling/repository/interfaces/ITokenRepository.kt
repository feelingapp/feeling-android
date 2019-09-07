package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel

interface ITokenRepository {
    val tokenModel: LiveData<TokenModel>

    suspend fun exchangeCodeForToken(getTokenModel: GetTokenModel)

    suspend fun saveToken(tokenModel: TokenModel)

    suspend fun clearToken()

    fun hasValidToken(): Boolean
}
