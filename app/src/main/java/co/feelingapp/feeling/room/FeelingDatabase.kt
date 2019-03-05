package co.feelingapp.feeling.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.feelingapp.feeling.room.converters.OffsetDateTimeConverter
import co.feelingapp.feeling.room.dao.FeelingDao
import co.feelingapp.feeling.room.dao.QuoteDao
import co.feelingapp.feeling.room.dao.SettingDao
import co.feelingapp.feeling.room.dao.UserDao
import co.feelingapp.feeling.room.entities.Feeling
import co.feelingapp.feeling.room.entities.Quote
import co.feelingapp.feeling.room.entities.Setting
import co.feelingapp.feeling.room.entities.User

@Database(entities = [User::class, Feeling::class, Setting::class, Quote::class], version = 1)
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
