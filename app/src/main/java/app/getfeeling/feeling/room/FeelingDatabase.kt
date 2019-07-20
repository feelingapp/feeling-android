package app.getfeeling.feeling.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.getfeeling.feeling.room.converters.OffsetDateTimeConverter
import app.getfeeling.feeling.room.dao.FeelingDao
import app.getfeeling.feeling.room.dao.QuoteDao
import app.getfeeling.feeling.room.dao.SettingDao
import app.getfeeling.feeling.room.dao.UserDao
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.room.entities.Quote
import app.getfeeling.feeling.room.entities.Setting
import app.getfeeling.feeling.room.entities.User

// TODO Remove exportSchema once we have settled on db design
@Database(entities = [User::class, Feeling::class, Setting::class, Quote::class], version = 1, exportSchema = false)
@TypeConverters(OffsetDateTimeConverter::class)
abstract class FeelingDatabase : RoomDatabase() {
    abstract fun feelingDao(): FeelingDao
    abstract fun userDao(): UserDao
    abstract fun quoteDao(): QuoteDao
    abstract fun settingDao(): SettingDao

    companion object {
        var INSTANCE: FeelingDatabase? = null

        fun getFeelingDatabase(context: Context): FeelingDatabase {
            if (INSTANCE == null) {
                synchronized(FeelingDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FeelingDatabase::class.java,
                        "feelingRoomDatabase"
                    ).fallbackToDestructiveMigration().build()
                    // TODO Remove fallback once we have settled on db design
                }
            }

            return INSTANCE ?: throw IllegalStateException("Database hasn't been created")
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}