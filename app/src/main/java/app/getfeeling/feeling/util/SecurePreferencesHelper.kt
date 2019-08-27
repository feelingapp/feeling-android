package app.getfeeling.feeling.util

import android.content.Context
import de.adorsys.android.securestoragelibrary.SecurePreferences

object SecurePreferencesHelper {
    private const val chunkSize = 240

    private fun getNumberOfChunksKey(key: String) = "${key}_numberOfChunks"

    fun setValue(context: Context, key: String, value: String) {
        val chunks = value.chunked(chunkSize)

        SecurePreferences.setValue(context, getNumberOfChunksKey(key), chunks.size)

        chunks.forEachIndexed { index, chunk ->
            SecurePreferences.setValue(context, "$key$index", chunk)
        }
    }

    fun getValue(context: Context, key: String): String? {
        val numberOfChunks = SecurePreferences.getIntValue(context, getNumberOfChunksKey(key), 0)

        if (numberOfChunks == 0) {
            return null
        }

        return (0 until numberOfChunks)
            .map { index ->
                val string = SecurePreferences.getStringValue(context, "$key$index", null) ?: run {
                    return null
                }

                string
            }.reduce { accumulator, chunk -> accumulator + chunk }
    }

    fun removeLongStringValue(context: Context, key: String) {
        val numberOfChunks = SecurePreferences.getIntValue(context, getNumberOfChunksKey(key), 0)

        (0 until numberOfChunks).map { SecurePreferences.removeValue(context, "$key$it") }
        SecurePreferences.removeValue(context, getNumberOfChunksKey(key))
    }

    fun containsLongStringValue(context: Context, key: String): Boolean {
        return SecurePreferences.contains(context, getNumberOfChunksKey(key))
    }
}
