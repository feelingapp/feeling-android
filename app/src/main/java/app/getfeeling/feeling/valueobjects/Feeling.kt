package app.getfeeling.feeling.valueobjects

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import app.getfeeling.feeling.util.Emotion
import com.squareup.moshi.JsonClass
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.YearMonth
import java.util.*

@Entity(
    tableName = "feelings",
    indices = [Index(value = ["userId"], unique = false)],
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId")
    )]
)
@JsonClass(generateAdapter = true)
data class Feeling(
    @PrimaryKey
    var id: String,

    var userId: String,

    var emotion: Emotion,

    var description: String,

    var hashtags: List<String>,

    var createdAt: OffsetDateTime,

    var updatedAt: OffsetDateTime
) {
    constructor(
        userId: String,
        emotion: Emotion,
        description: String,
        hashtags: List<String>
    ) : this(
        UUID.randomUUID().toString(),
        userId,
        emotion,
        description,
        hashtags,
        OffsetDateTime.now(),
        OffsetDateTime.now()
    )

    val yearMonth: YearMonth
        get() = YearMonth.from(createdAt)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Feeling

        if (id != other.id) return false
        if (emotion != other.emotion) return false
        if (description != other.description) return false
        if (hashtags != other.hashtags) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + emotion.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + hashtags.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}
