package app.getfeeling.feeling.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var quote: String,
    var author: String,
    var emotion: String
) {
    constructor(quote: String, author: String, emotion: String) : this(0, quote, author, emotion)
}
