package app.getfeeling.feeling.room.converters

import androidx.room.TypeConverter
import app.getfeeling.feeling.room.FeelingDatabase
import app.getfeeling.feeling.valueobjects.Settings

object DailyReminderConverter {
    private val adapter = FeelingDatabase.moshi.adapter(Settings.DailyReminder::class.java)

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
