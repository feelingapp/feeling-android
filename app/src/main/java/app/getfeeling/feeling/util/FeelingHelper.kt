package app.getfeeling.feeling.util

import app.getfeeling.feeling.R

object FeelingHelper {

    fun getEmojiAndColour(emotion: String): Pair<Int, Int> =
        when (emotion) {
            Emotion.AMAZING -> Pair(R.drawable.ic_emoji_amazing_face, R.color.emotionAmazing)
            Emotion.GREAT -> Pair(R.drawable.ic_emoji_great_face, R.color.emotionGreat)
            Emotion.UNSURE -> Pair(R.drawable.ic_emoji_unsure_face, R.color.emotionUnsure)
            Emotion.ANGRY -> Pair(R.drawable.ic_emoji_angry_face, R.color.emotionAngry)
            Emotion.UPSET -> Pair(R.drawable.ic_emoji_upset_face, R.color.emotionUpset)

            else -> throw NotImplementedError(emotion)
        }
}
