package app.getfeeling.feeling.room.converters

import androidx.room.TypeConverter
import app.getfeeling.feeling.valueobjects.Settings
import com.squareup.moshi.Moshi

object DailyReminderConverter {
    private val adapter = Moshi.Builder().build().adapter(Settings.DailyReminder::class.java)

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
