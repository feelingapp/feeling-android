package app.getfeeling.feeling.ui.signin

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.TrustedWebUtils
import androidx.lifecycle.AndroidViewModel
import app.getfeeling.feeling.BuildConfig


class SignInViewModel(application: Application) : AndroidViewModel(application) {
    fun continueWithEmail() {
        val context = getApplication<Application>().applicationContext

        val customTabsIntent = CustomTabsIntent.Builder()
            .build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val uri = Uri.Builder()
            .scheme("https")
            .authority("getfeeling.app")
            .appendPath("/authorize")
            .appendQueryParameter("client_id", BuildConfig.FEELING_API_CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", BuildConfig.FEELING_API_REDIRECT_URI)
            .appendQueryParameter("code_challenge_method", "")
            .appendQueryParameter("code_challenge", "")
            .appendQueryParameter("code_challenge_hash", "")
            .build()

        print(uri)

        TrustedWebUtils.launchAsTrustedWebActivity(
            context,
            customTabsIntent,
            uri
        )
    }
}
