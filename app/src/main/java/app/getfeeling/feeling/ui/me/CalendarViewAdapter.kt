package app.getfeeling.feeling.ui.me

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.room.entities.Feeling
import org.threeten.bp.format.TextStyle
import java.util.*

class CalendarViewAdapter(private val feelings: List<Feeling>) :
    RecyclerView.Adapter<CalendarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val calendarMonth = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_month, parent, false) as CalendarMonthView

        calendarMonth.feelingsGrid.adapter = FeelingMonthAdapter()

        return CalendarViewHolder(calendarMonth)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.calendarMonth.apply {
            month.text = feelings[position].createdAt.month.getDisplayName(
                TextStyle.FULL_STANDALONE,
                Locale.ENGLISH
            )
        }
    }

    override fun getItemCount() = feelings.size
}
