package app.getfeeling.feeling.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class SettingsFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener {
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<SettingsViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "terms_and_conditions" -> openPage("https://getfeeling.app/terms")
            "privacy_policy" -> openPage("https://getfeeling.app/privacy")
            "daily_reminder" -> mainNavController?.navigate(R.id.action_settings_fragment_to_daily_reminder_fragment)
            "sign_out" -> signOut()
        }

        return super.onPreferenceTreeClick(preference)
    }

    private fun openPage(uri: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        customTabsIntent.launchUrl(context, Uri.parse(uri))
    }

    private fun signOut() {
        viewModel.signOut()
        mainNavController?.navigate(R.id.action_settings_fragment_to_me_fragment)
    }
}
