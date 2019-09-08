package app.getfeeling.feeling.ui.settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import app.getfeeling.feeling.R
import org.threeten.bp.OffsetDateTime


class DailyReminderFragment : PreferenceFragmentCompat(),
    PreferenceManager.OnPreferenceTreeClickListener, TimePickerDialog.OnTimeSetListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.daily_reminder_preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        when (preference.key) {
            "daily_reminder_time_picker" -> {
                val time = OffsetDateTime.now()
                val hour = time.hour
                val minute = time.minute

                val timePicker = TimePickerDialog(activity, this, hour, minute, true)
                timePicker.show()
            }
        }

        return super.onPreferenceTreeClick(preference)
    }

    override fun onTimeSet(view: TimePicker, hour: Int, minute: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putInt("daily_reminder_hour", hour)
        editor.putInt("daily_reminder_minute", minute)
        editor.apply()
    }
}
