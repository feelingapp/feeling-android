package app.getfeeling.feeling.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "users")
@JsonClass(generateAdapter = true)
data class User(
    @PrimaryKey
    val id: String,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "last_name")
    val lastName: String,

    val email: String,

    val verified: Boolean
)
