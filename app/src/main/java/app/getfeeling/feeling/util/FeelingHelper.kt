package app.getfeeling.feeling.util

import app.getfeeling.feeling.R

object FeelingHelper {

    fun getEmojiAndColour(emotion: String): Pair<Int, Int> =
        when (emotion) {
            "Angry" -> Pair(R.drawable.ic_emoji_angry_face, R.color.emotionAngry)
            "Happy" -> Pair(
                R.drawable.ic_emoji_grinning_face_with_smiling_eyes,
                R.color.emotionHappy
            )
            "Sad" -> Pair(R.drawable.ic_emoji_loudly_crying_face, R.color.emotionSad)
            "Neutral" -> Pair(R.drawable.ic_emoji_neutral_face, R.color.emotionNeutral)
            "Loving" -> Pair(
                R.drawable.ic_emoji_smiling_face_with_heart_eyes,
                R.color.emotionLoving
            )
            else -> throw NotImplementedError(emotion)
        }
}
