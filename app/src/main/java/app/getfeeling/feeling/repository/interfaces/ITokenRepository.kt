package app.getfeeling.feeling.repository.interfaces

import androidx.lifecycle.LiveData
import app.getfeeling.feeling.valueobjects.Token
import app.getfeeling.feeling.valueobjects.TokenRequest

interface ITokenRepository {
    val token: LiveData<Token>

    suspend fun exchangeCodeForToken(tokenRequest: TokenRequest)

    suspend fun saveToken(token: Token)

    suspend fun clearToken()

    fun hasValidToken(): Boolean

    fun getUserId(): String?
}
