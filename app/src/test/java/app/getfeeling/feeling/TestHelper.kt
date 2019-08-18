package app.getfeeling.feeling

import java.io.File

class TestHelper {
    companion object {
        fun getJson(path: String): String {
            val uri = TestHelper::class.java.classLoader!!.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }
    }
}
