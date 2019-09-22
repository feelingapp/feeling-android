package app.getfeeling.feeling.room.dao

import androidx.room.*
import app.getfeeling.feeling.valueobjects.Settings

@Dao
interface SettingDao {
    @Insert
    fun insert(setting: Settings)

    @Update
    fun update(setting: Settings)

    @Delete
    fun delete(setting: Settings)

    @Query("SELECT dailyReminder FROM settings WHERE userId = :userId")
    fun getDailyReminder(userId: Int): String
}
