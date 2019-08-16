package app.getfeeling.feeling.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import app.getfeeling.feeling.R

class DailyReminderFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_daily_reminder, rootKey)
    }
}
