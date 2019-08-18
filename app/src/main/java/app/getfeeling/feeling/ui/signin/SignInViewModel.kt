package app.getfeeling.feeling.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.api.models.GetTokenModel
import app.getfeeling.feeling.api.models.GrantType
import app.getfeeling.feeling.api.models.TokenModel
import app.getfeeling.feeling.repository.interfaces.IFeelingRepository
import app.getfeeling.feeling.util.PKCE
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val repository: IFeelingRepository) :
    ViewModel() {

    private lateinit var state: String
    private lateinit var codeVerifier: String
    private val getTokenModel = MutableLiveData<GetTokenModel>()
    val tokenModel: LiveData<TokenModel> = Transformations.switchMap(getTokenModel) {
        repository.exchangeCodeForToken(it)
    }


    fun generateCodeChallenge(): String {
        val pkce = PKCE()
        codeVerifier = pkce.generateCodeVerifier()

        return pkce.generateCodeChallenge(codeVerifier)
    }

    fun generateState(): String {
        val stateCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toList().toTypedArray()
        state = (1..32).map { stateCharacters.random() }.joinToString("")

        return state
    }

    fun callback(authorizationCode: String, receivedState: String) {
        if (state != receivedState) return

        getTokenModel.value = GetTokenModel(
            GrantType.AUTHORIZATION_CODE,
            authorizationCode,
            codeVerifier,
            BuildConfig.FEELING_API_CLIENT_ID
        )
    }
}
