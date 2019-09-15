package app.getfeeling.feeling.util

import kotlin.random.Random

object Emotion {
    const val AMAZING = "Amazing"
    const val GREAT = "Great"
    const val UNSURE = "Unsure"
    const val ANGRY = "Angry"
    const val UPSET = "Upset"

    private val values = listOf(ANGRY, AMAZING, GREAT, UNSURE, UPSET)

    fun getRandom(): String = values[Random.nextInt(0, values.size)]
}
