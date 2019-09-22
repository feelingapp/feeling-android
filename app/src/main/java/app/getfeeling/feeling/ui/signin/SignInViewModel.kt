package app.getfeeling.feeling.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.util.PKCE
import app.getfeeling.feeling.valueobjects.GrantType
import app.getfeeling.feeling.valueobjects.Token
import app.getfeeling.feeling.valueobjects.TokenRequest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val repository: ITokenRepository) :
    ViewModel() {

    val token: LiveData<Token> = repository.token

    private lateinit var state: String
    private lateinit var codeVerifier: String

    fun generateCodeChallenge(): String {
        val pkce = PKCE()
        codeVerifier = pkce.generateCodeVerifier()

        return pkce.generateCodeChallenge(codeVerifier)
    }

    fun generateState(): String {
        val stateCharacters =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toList().toTypedArray()
        state = (1..32).map { stateCharacters.random() }.joinToString("")

        return state
    }

    fun handleAuthorizationCallback(authorizationCode: String, receivedState: String) {
        if (state != receivedState) return

        val tokenRequest = TokenRequest(
            GrantType.AUTHORIZATION_CODE,
            authorizationCode,
            codeVerifier,
            BuildConfig.FEELING_API_CLIENT_ID
        )

        viewModelScope.launch {
            repository.exchangeCodeForToken(tokenRequest)
        }
    }

    fun isSignedIn() = repository.hasValidToken()

    fun signOut() {
        viewModelScope.launch {
            repository.clearToken()
        }
    }
}
