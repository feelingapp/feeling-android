package app.getfeeling.feeling.util

import app.getfeeling.feeling.R

object FeelingHelper {

    fun getEmojiAndColour(emotion: String): Pair<Int, Int> =
        when (emotion) {
            "Angry" -> Pair(R.drawable.ic_emoji_angry_face, R.color.emotionAngry)
            "Great" -> Pair(
                R.drawable.ic_emoji_great_face,
                R.color.emotionGreat
            )
            "Upset" -> Pair(R.drawable.ic_emoji_upset_face, R.color.emotionUpset)
            "Unsure" -> Pair(R.drawable.ic_emoji_unsure_face, R.color.emotionUnsure)
            "Amazing" -> Pair(
                R.drawable.ic_emoji_amazing_face,
                R.color.emotionAmazing
            )
            else -> throw NotImplementedError(emotion)
        }
}
