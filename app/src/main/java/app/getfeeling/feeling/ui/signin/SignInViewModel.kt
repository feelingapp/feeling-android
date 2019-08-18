package app.getfeeling.feeling.ui.signin

import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.util.PKCE
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val repository: IFeelingRepository) :
    ViewModel() {

    private var state: String? = null
    private var codeVerifier: String? = null

    fun generateCodeChallenge(): String {
        val pkce = PKCE()
        codeVerifier = pkce.generateCodeVerifier()

        return pkce.generateCodeChallenge(codeVerifier as String)
    }

    fun generateState(): String {
        val stateCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toList().toTypedArray()
        state = (1..32).map { stateCharacters.random() }.joinToString("")

        return state as String
    }

    fun callback(authorizationCode: String, receivedState: String) {
        if (state != receivedState) return

        val response = repository.exchangeCodeForToken(
            GetTokenModel(
                authorizationCode,
                codeVerifier as String,
                BuildConfig.FEELING_API_CLIENT_ID
            )
        )
    }
}
