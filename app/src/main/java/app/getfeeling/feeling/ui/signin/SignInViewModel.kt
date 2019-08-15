package app.getfeeling.feeling.ui.signin

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.TrustedWebUtils
import androidx.lifecycle.AndroidViewModel
import app.getfeeling.feeling.BuildConfig
import app.getfeeling.feeling.util.PKCE

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private var state: String? = null

    fun continueWithEmail() {
        val context = getApplication<Application>().applicationContext

        val customTabsIntent = CustomTabsIntent.Builder()
            .build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pkce = PKCE()
        val codeVerifier = pkce.generateCodeVerifier()
        val codeChallenge = pkce.generateCodeChallenge(codeVerifier)
        state = generateState()

        val uri = Uri.Builder()
            .scheme("http")
            .authority(BuildConfig.FEELING_WEBSITE_URL)
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.FEELING_API_CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", BuildConfig.FEELING_API_REDIRECT_URI)
            .appendQueryParameter("code_challenge_method", PKCE.CODE_CHALLENGE_METHOD)
            .appendQueryParameter("code_challenge", codeChallenge)
            .appendQueryParameter("state", state)
            .build()

        TrustedWebUtils.launchAsTrustedWebActivity(
            context,
            customTabsIntent,
            uri
        )
    }

    private fun generateState(): String {
        val stateCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toList().toTypedArray()
        return (1..32).map { stateCharacters.random() }.joinToString("")
    }

    fun callback(authorizationCode: String, receivedState: String) {
        if (state != receivedState) return
    }
}
