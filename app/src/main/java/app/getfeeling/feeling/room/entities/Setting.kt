package app.getfeeling.feeling.room.entities

import androidx.room.*
import app.getfeeling.feeling.models.User

@Entity(
    tableName = "settings",
    indices = [Index(value = ["user_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("user_id")
    )]
)
data class Setting(
    @PrimaryKey @ColumnInfo(name = "user_id") var userId: Int,
    @ColumnInfo(name = "daily_reminder_json") var dailyReminder: String
)
