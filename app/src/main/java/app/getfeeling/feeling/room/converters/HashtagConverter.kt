package app.getfeeling.feeling.room.converters

import androidx.room.TypeConverter

object HashtagConverter {
    @TypeConverter
    @JvmStatic
    fun fromString(stringListString: String): List<String> = stringListString.split(",").map { it }

    @TypeConverter
    @JvmStatic
    fun toString(stringList: List<String>): String = stringList.joinToString(separator = ",")
}
