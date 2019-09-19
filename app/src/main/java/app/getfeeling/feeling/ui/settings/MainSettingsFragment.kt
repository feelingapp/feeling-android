package app.getfeeling.feeling.ui.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R
import app.getfeeling.feeling.ui.signin.SignInViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainSettingsFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by activityViewModels<SignInViewModel> { viewModelFactory }

    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    private val settingsNavController: NavController? by lazy { activity?.findNavController(R.id.settings_nav_host_fragment) }

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
            "daily_reminder" -> settingsNavController?.navigate(R.id.action_mainSettingsFragment_to_dailyReminderFragment)
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
        viewModel.tokenModel.observe(this, Observer {
            if (it == null) {
                mainNavController?.popBackStack()
            }
        })
    }
}
