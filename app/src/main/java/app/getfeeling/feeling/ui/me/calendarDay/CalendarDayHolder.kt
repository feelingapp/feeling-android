package app.getfeeling.feeling.ui.me.calendarDay

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarDayBinding
import app.getfeeling.feeling.ui.me.FeelingMonth
import app.getfeeling.feeling.util.setColorFilter
import org.threeten.bp.OffsetDateTime

class CalendarDayHolder(private val context: Context, val binding: CalendarDayBinding) {

    var feelingId: String = ""

    fun bind(position: Int, feelingMonth: FeelingMonth) {
        val imageEmoji = binding.imageEmoji
        if (position < feelingMonth.dayOffset) {
            imageEmoji.visibility = View.INVISIBLE
        } else {
            val background = getDrawable(R.drawable.round_rect_shape)!! as GradientDrawable

            // Give border to current day
            if (isCurrentDay(position, feelingMonth)) {
                background.setStroke(6, getColour(R.color.emotionBorder))
            } else {
                // for some reason I have to clear the stroke
                background.setStroke(0, 0)
            }

            // Give emoji and colour to each day
            with(feelingMonth.getWithOffset(position)) {
                if (this == null) {
                    background.setColorFilter(getColour(R.color.emotionNone))
                } else {
                    feelingId = id
                    with(emotion) {
                        imageEmoji.setImageResource(getEmoji())
                        background.setColorFilter(getColour(getColour()))
                    }
                }
            }
            imageEmoji.background = background
        }
    }

    private fun isCurrentDay(
        position: Int,
        feelingMonth: FeelingMonth
    ): Boolean = with(OffsetDateTime.now()) {
        (position - feelingMonth.dayOffset) == (dayOfMonth - 1)
                && feelingMonth.monthValue == monthValue
                && feelingMonth.year == year
    }

    private fun getColour(colour: Int) = ContextCompat.getColor(context, colour)

    private fun getDrawable(drawable: Int) = ContextCompat.getDrawable(context, drawable)
}
