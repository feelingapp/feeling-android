package app.getfeeling.feeling.ui.me.calendarDay

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarDayBinding
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.util.FeelingHelper.getEmojiAndColour
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class CalendarDayAdapter @Inject constructor(private val context: Context) :
    AbstractCalendarDayAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val binding = CalendarDayBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )

            val buttonDay = binding.buttonDay
            if (position < feelingMonth.dayOffset) {
                buttonDay.visibility = View.INVISIBLE
            } else {
                val background = getDrawable(R.drawable.round_rect_shape)!! as GradientDrawable
                if ((position - feelingMonth.dayOffset) == (OffsetDateTime.now().dayOfMonth - 1)
                    && feelingMonth.monthValue == OffsetDateTime.now().monthValue
                ) {
                    background.setStroke(6, getColour(R.color.emotionBorder))
                } else {
                    // for some reason I have to clear the stroke
                    background.setStroke(0, 0)
                }

                if (feelingMonth[position] == null) {
                    buttonDay.isClickable = false
                    background.mutate().setColorFilter(
                        getColour(R.color.emotionNone),
                        PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    val (emoji, colour) = getEmojiAndColour(feelingMonth[position]!!.emotion)
                    buttonDay.setImageResource(emoji)
                    background.mutate().setColorFilter(getColour(colour), PorterDuff.Mode.MULTIPLY)
                }
                buttonDay.background = background
            }

            return binding.root
        }

        return convertView
    }

    override fun getItem(position: Int): Feeling? = feelingMonth[position]

    override fun getItemId(position: Int): Long {
        feelingMonth[position]?.let {
            return it.id.toLong()
        }

        return -1
    }

    override fun getCount(): Int = feelingMonth.monthLength + feelingMonth.dayOffset

    private fun getColour(colour: Int) =
        ContextCompat.getColor(context, colour)

    private fun getDrawable(drawable: Int) =
        ContextCompat.getDrawable(context, drawable)
}

