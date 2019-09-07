package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.TokenModel

interface ITokenRepository {
    fun exchangeCodeForToken(getTokenModel: GetTokenModel): LiveData<TokenModel>

    fun saveToken(tokenModel: TokenModel)

    fun getToken(): TokenModel?

    fun hasValidToken(): Boolean

    fun clearToken()
}
