package app.getfeeling.feeling.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SettingsModel(
    @Json(name = "daily_reminder") val dailyReminder: DailyReminder
) {
    data class DailyReminder(
        val enabled: Boolean,
        val hour: Int,
        val minute: Int
    )
}
