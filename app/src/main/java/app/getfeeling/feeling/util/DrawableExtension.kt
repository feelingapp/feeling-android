package app.getfeeling.feeling.util

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build

// if more modes are needed see
// https://stackoverflow.com/questions/56716093/setcolorfilter-is-deprecated-on-api29/57054627#57054627
fun Drawable.setColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
    } else {
        @Suppress("DEPRECATION")
        setColorFilter(color, PorterDuff.Mode.MULTIPLY)
    }
}
