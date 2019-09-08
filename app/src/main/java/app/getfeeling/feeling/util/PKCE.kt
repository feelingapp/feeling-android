package app.getfeeling.feeling.util

import android.util.Base64
import java.security.MessageDigest
import java.security.SecureRandom

class PKCE {
    companion object {
        const val CODE_CHALLENGE_METHOD = "SHA-256"
    }

    fun generateCodeVerifier(length: Int = 64): String {
        val bytes = ByteArray(length)

        val random = SecureRandom()
        random.nextBytes(bytes)

        return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }

    fun generateCodeChallenge(codeVerifier: String): String {
        val codeVerifierBytes = codeVerifier.toByteArray()
        val messageDigest = MessageDigest.getInstance(CODE_CHALLENGE_METHOD)
        messageDigest.update(codeVerifierBytes)
        val codeChallengeBytes = messageDigest.digest()

        return Base64.encodeToString(
            codeChallengeBytes,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }
}
