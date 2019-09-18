package app.getfeeling.feeling.room.entities

import androidx.room.*
import app.getfeeling.feeling.models.User
import app.getfeeling.feeling.util.Emotion
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.YearMonth

@Entity(
    tableName = "feelings",
    indices = [Index(value = ["user_id"], unique = false)],
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id")
    )]
)
data class Feeling(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "user_id") var userId: Int,
    var emotion: Emotion,
    var description: String,
    var hashtags: String,
    @ColumnInfo(name = "created_at") var createdAt: OffsetDateTime,
    @ColumnInfo(name = "updated_at") var updatedAt: OffsetDateTime
) {
    constructor(
        userId: Int,
        emotion: Emotion,
        description: String,
        hashtags: String,
        createdAt: OffsetDateTime
    ) : this(
        0,
        userId,
        emotion,
        description,
        hashtags,
        createdAt,
        OffsetDateTime.now()
    )

    fun getYearMonth(): YearMonth = YearMonth.from(createdAt)
}
