package co.feelingapp.feeling.room.dao

import androidx.room.*
import co.feelingapp.feeling.room.entities.Setting

@Dao
interface SettingDao {
    @Insert
    fun insert(setting: Setting)

    @Update
    fun update(setting: Setting)

    @Delete
    fun delete(setting: Setting)

    @Query("SELECT daily_reminder_json FROM settings WHERE user_id = :userId")
    fun getDailyReminder(userId: Int): String
}
