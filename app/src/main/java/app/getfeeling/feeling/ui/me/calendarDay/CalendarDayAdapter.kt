package app.getfeeling.feeling.ui.me.calendarDay

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarDayBinding
import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class CalendarDayAdapter @Inject constructor(private val context: Context) :
    AbstractCalendarDayAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val binding = DataBindingUtil.inflate<CalendarDayBinding>(
                LayoutInflater.from(parent?.context),
                R.layout.calendar_day,
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
                    background.mutate().setColorFilter(colour, PorterDuff.Mode.MULTIPLY)
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

    private fun getEmojiAndColour(emotion: String): Pair<Int, Int> =
        when (emotion) {
            "Angry" -> Pair(R.drawable.ic_emoji_angry_face, getColour(R.color.emotionAngry))
            "Happy" -> Pair(
                R.drawable.ic_emoji_grinning_face_with_smiling_eyes, getColour(R.color.emotionHappy)
            )
            "Sad" -> Pair(R.drawable.ic_emoji_loudly_crying_face, getColour(R.color.emotionSad))
            "Neutral" -> Pair(R.drawable.ic_emoji_neutral_face, getColour(R.color.emotionNeutral))
            "Loving" -> Pair(
                R.drawable.ic_emoji_smiling_face_with_heart_eyes, getColour(R.color.emotionLoving)
            )
            else -> throw NotImplementedError()
        }

    private fun getColour(colour: Int) =
        ContextCompat.getColor(context, colour)

    private fun getDrawable(drawable: Int) =
        ContextCompat.getDrawable(context, drawable)
}

