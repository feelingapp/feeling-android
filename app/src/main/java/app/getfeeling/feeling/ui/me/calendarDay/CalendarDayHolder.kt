package app.getfeeling.feeling.ui.me.calendarDay

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarDayBinding
import app.getfeeling.feeling.ui.me.FeelingMonth
import org.threeten.bp.OffsetDateTime

class CalendarDayHolder(private val context: Context, val binding: CalendarDayBinding) {

    fun bind(position: Int, feelingMonth: FeelingMonth) {
        val imageEmoji = binding.imageEmoji
        if (position < feelingMonth.dayOffset) {
            imageEmoji.visibility = View.INVISIBLE
        } else {
            val background = getDrawable(R.drawable.round_rect_shape)!! as GradientDrawable

            // Give border to current day
            if ((position - feelingMonth.dayOffset) == (OffsetDateTime.now().dayOfMonth - 1)
                && feelingMonth.monthValue == OffsetDateTime.now().monthValue
            ) {
                background.setStroke(6, getColour(R.color.emotionBorder))
            } else {
                // for some reason I have to clear the stroke
                background.setStroke(0, 0)
            }

            // Give emoji and colour to each day
            with(feelingMonth.getWithOffset(position)) {
                if (this == null) {
                    background.mutate()
                        .setColorFilter(getColour(R.color.emotionNone), PorterDuff.Mode.MULTIPLY)
                } else {
                    with(emotion) {
                        imageEmoji.setImageResource(getEmoji())
                        background.mutate()
                            .setColorFilter(getColour(getColour()), PorterDuff.Mode.MULTIPLY)
                    }
                }
            }
            imageEmoji.background = background
        }
    }

    private fun getColour(colour: Int) = ContextCompat.getColor(context, colour)

    private fun getDrawable(drawable: Int) = ContextCompat.getDrawable(context, drawable)
}
