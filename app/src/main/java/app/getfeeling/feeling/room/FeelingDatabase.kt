package app.getfeeling.feeling.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.getfeeling.feeling.room.converters.EmotionConverter
import app.getfeeling.feeling.models.User
import app.getfeeling.feeling.room.converters.OffsetDateTimeConverter
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.dao.QuoteDao
import app.getfeeling.feeling.room.dao.SettingDao
import app.getfeeling.feeling.room.dao.UserDao
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.Quote
import app.getfeeling.feeling.room.entities.Setting


// TODO Remove exportSchema once we have settled on db design
@Database(
    entities = [User::class, Feeling::class, Setting::class, Quote::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OffsetDateTimeConverter::class, EmotionConverter::class)
abstract class FeelingDatabase : RoomDatabase() {
    abstract fun feelingDao(): FeelingDao
    abstract fun userDao(): UserDao
    abstract fun quoteDao(): QuoteDao
    abstract fun settingDao(): SettingDao
}
