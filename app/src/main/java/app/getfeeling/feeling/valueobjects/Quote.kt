package app.getfeeling.feeling.valueobjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.getfeeling.feeling.util.Emotion
import com.squareup.moshi.JsonClass

@Entity(tableName = "quotes")
@JsonClass(generateAdapter = true)
data class Quote(
    @PrimaryKey
    var id: String,

    var quote: String,

    var author: String,

    var emotion: Emotion
)
