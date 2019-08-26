package app.getfeeling.feeling.ui.settings

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R


class SettingsFragment : PreferenceFragmentCompat(), PreferenceManager.OnPreferenceTreeClickListener {
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        if (preference.key == "sign_out") {
            mainNavController?.navigate(R.id.action_settings_fragment_to_sign_in_fragment)
        }

        return super.onPreferenceTreeClick(preference)
    }
}
