package app.getfeeling.feeling.ui.settings

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R

class DailyReminderFragment : PreferenceFragmentCompat(), PreferenceManager.OnPreferenceTreeClickListener {
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment) }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_daily_reminder, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "daily_reminder_time_picker" -> mainNavController?.navigate(R.id.action_daily_reminder_fragment_to_time_picker_fragment)
        }

        return super.onPreferenceTreeClick(preference)
    }
}
