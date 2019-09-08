package app.getfeeling.feeling.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R


class MainSettingsFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener {
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private val settingsNavController: NavController? by lazy { activity?.findNavController(R.id.settings_nav_host_fragment) }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "terms_and_conditions" -> openPage("https://getfeeling.app/terms")
            "privacy_policy" -> openPage("https://getfeeling.app/privacy")
            "daily_reminder" -> settingsNavController?.navigate(R.id.action_mainSettingsFragment_to_dailyReminderFragment)
            "sign_out" -> mainNavController?.popBackStack()
        }

        return super.onPreferenceTreeClick(preference)
    }

    private fun openPage(uri: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        customTabsIntent.launchUrl(context, Uri.parse(uri))
    }
}
