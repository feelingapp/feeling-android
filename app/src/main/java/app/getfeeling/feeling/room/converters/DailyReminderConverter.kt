package app.getfeeling.feeling.room.converters

import androidx.room.TypeConverter
import app.getfeeling.feeling.room.FeelingDatabase
import app.getfeeling.feeling.valueobjects.Settings
import app.getfeeling.feeling.valueobjects.Settings_DailyReminderJsonAdapter

object DailyReminderConverter {
    private val adapter = Settings_DailyReminderJsonAdapter(FeelingDatabase.moshi)

    @TypeConverter
    @JvmStatic
    fun fromString(json: String): Settings.DailyReminder {
        return adapter.fromJson(json)!!
    }

    @TypeConverter
    @JvmStatic
    fun toString(dailyReminder: Settings.DailyReminder): String {
        return adapter.toJson(dailyReminder)
    }
}
