package app.getfeeling.feeling.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.GrantType
import app.getfeeling.feeling.api.models.TokenModel
import app.getfeeling.feeling.repository.interfaces.ITokenRepository
import app.getfeeling.feeling.util.PKCE
import javax.inject.Inject


class SignInViewModel @Inject constructor(private val repository: ITokenRepository) :
    ViewModel() {

    val tokenModel: LiveData<TokenModel> = repository.tokenModel

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

        val getTokenModel = GetTokenModel(
            GrantType.AUTHORIZATION_CODE,
            authorizationCode,
            codeVerifier,
            BuildConfig.FEELING_API_CLIENT_ID
        )

        repository.exchangeCodeForToken(getTokenModel)
    }

    fun isSignedIn() = repository.hasValidToken()
}
