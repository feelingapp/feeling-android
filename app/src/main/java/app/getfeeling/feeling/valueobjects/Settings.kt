package app.getfeeling.feeling.valueobjects

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    tableName = "settings",
    indices = [Index(value = ["userId"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId")
    )]
)
@JsonClass(generateAdapter = true)
data class Settings(
    @PrimaryKey
    @Json(name = "user_id")
    var userId: String,

    @Json(name = "daily_reminder")
    var dailyReminder: DailyReminder
) {
    @JsonClass(generateAdapter = true)
    data class DailyReminder(
        val enabled: Boolean,
        val hour: Int,
        val minute: Int
    )
}
