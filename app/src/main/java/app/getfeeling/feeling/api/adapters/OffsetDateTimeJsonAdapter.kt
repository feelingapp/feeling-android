package app.getfeeling.feeling.api.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.OffsetDateTime

class OffsetDateTimeJsonAdapter {

    @ToJson
    fun toJson(offsetDateTime: OffsetDateTime): String = offsetDateTime.toString()

    @FromJson
    fun fromJson(offsetDateTime: String): OffsetDateTime = OffsetDateTime.parse(offsetDateTime)
}
