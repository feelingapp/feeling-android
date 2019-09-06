package app.getfeeling.feeling.ui.me.calendarDay

import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import app.getfeeling.feeling.FeelingApp
import app.getfeeling.feeling.R
import app.getfeeling.feeling.room.entities.Feeling
import app.getfeeling.feeling.ui.me.FeelingMonth
import org.threeten.bp.OffsetDateTime

class CalendarDayAdapter(private val feelingMonth: FeelingMonth) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var calendarDay = convertView
        if (calendarDay == null) {
            calendarDay = LayoutInflater.from(parent?.context)
                .inflate(R.layout.calendar_day, parent, false)

            val buttonDay = calendarDay.findViewById<ImageButton>(R.id.button_day)
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
                        getColour(R.color.emojiNone),
                        PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    val (emoji, colour) = getEmojiAndColour(feelingMonth[position]!!.emotion)
                    buttonDay.setImageResource(emoji)
                    background.mutate().setColorFilter(colour, PorterDuff.Mode.MULTIPLY)
                }
                buttonDay.background = background
            }
        }

        return calendarDay!!
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
            "Angry" -> Pair(R.drawable.ic_emoji_angry_face, getColour(R.color.emojiAngry))
            "Happy" -> Pair(
                R.drawable.ic_emoji_grinning_face_with_smiling_eyes, getColour(R.color.emojiHappy)
            )
            "Sad" -> Pair(R.drawable.ic_emoji_loudly_crying_face, getColour(R.color.emojiSad))
            "Neutral" -> Pair(R.drawable.ic_emoji_neutral_face, getColour(R.color.emojiNeutral))
            "Loving" -> Pair(
                R.drawable.ic_emoji_smiling_face_with_heart_eyes, getColour(R.color.emojiLoving)
            )
            else -> throw NotImplementedError()
        }

    private fun getColour(colour: Int) =
        ContextCompat.getColor(FeelingApp.applicationContext(), colour)

    private fun getDrawable(drawable: Int) =
        ContextCompat.getDrawable(FeelingApp.applicationContext(), drawable)
}

