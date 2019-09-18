package app.getfeeling.feeling.util

import app.getfeeling.feeling.R

enum class Emotion {
    AMAZING,
    GREAT,
    UNSURE,
    ANGRY,
    UPSET;

    fun getString() = when (this) {
        AMAZING -> R.string.emotion_amazing
        GREAT -> R.string.emotion_great
        UNSURE -> R.string.emotion_unsure
        ANGRY -> R.string.emotion_angry
        UPSET -> R.string.emotion_upset
    }

    fun getEmoji(): Int = when (this) {
        AMAZING -> R.drawable.ic_emoji_amazing_face
        GREAT -> R.drawable.ic_emoji_great_face
        UNSURE -> R.drawable.ic_emoji_unsure_face
        ANGRY -> R.drawable.ic_emoji_angry_face
        UPSET -> R.drawable.ic_emoji_upset_face
    }

    fun getColour(): Int = when (this) {
        AMAZING -> R.color.emotionAmazing
        GREAT -> R.color.emotionGreat
        UNSURE -> R.color.emotionUnsure
        ANGRY -> R.color.emotionAngry
        UPSET -> R.color.emotionUpset
    }
}
