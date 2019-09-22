package app.getfeeling.feeling.room.converters

import androidx.room.TypeConverter
import app.getfeeling.feeling.util.Emotion

object EmotionConverter {
    @TypeConverter
    @JvmStatic
    fun toEmotion(value: String): Emotion = Emotion.valueOf(value)

    @TypeConverter
    @JvmStatic
    fun toString(emotion: Emotion): String = emotion.toString()
}
