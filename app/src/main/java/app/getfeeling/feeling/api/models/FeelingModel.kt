package app.getfeeling.feeling.api.models

data class FeelingModel (
    val emotion: String,
    val description: String,
    val hashtags: Array<String>,
    val date: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FeelingModel

        if (emotion != other.emotion) return false
        if (description != other.description) return false
        if (!hashtags.contentEquals(other.hashtags)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = emotion.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + hashtags.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}
