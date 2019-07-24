package app.getfeeling.feeling.ui.signin

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.TrustedWebUtils
import androidx.lifecycle.AndroidViewModel


class SignInViewModel(application: Application) : AndroidViewModel(application) {
    fun continueWithEmail() {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        TrustedWebUtils.launchAsTrustedWebActivity(
            getApplication<Application>().applicationContext,
            customTabsIntent,
            Uri.parse("https://getfeeling.app/authorize")
        )
    }
}
