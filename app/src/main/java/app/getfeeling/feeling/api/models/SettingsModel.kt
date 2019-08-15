package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json

data class SettingsModel(
    @Json(name = "daily_reminder") val dailyReminder: DailyReminder
) {
    data class DailyReminder(
        val enabled: Boolean,
        val hour: Int,
        val minute: Int
    )
}
