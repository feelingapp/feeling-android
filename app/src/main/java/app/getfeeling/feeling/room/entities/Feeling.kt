package app.getfeeling.feeling.room.entities

import androidx.room.*
import org.threeten.bp.OffsetDateTime

@Entity(
    tableName = "feelings",
    indices = [Index(value = ["user_id"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id")
    )]
)
data class Feeling(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "user_id") var userId: Int,
    var emotion: String,
    var description: String,
    var hashtags: String,
    @ColumnInfo(name = "created_at") var createdAt: OffsetDateTime,
    @ColumnInfo(name = "updated_at") var updatedAt: OffsetDateTime
)
