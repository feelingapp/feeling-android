package app.getfeeling.feeling.ui.me

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R

class CalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthHolder>() {

    lateinit var months: Array<String>

    private lateinit var feelingCalendar: FeelingCalendar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarMonthHolder {
        val calendarMonth = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_month, parent, false)

        return CalendarMonthHolder(calendarMonth, months)
    }

    override fun onBindViewHolder(holder: CalendarMonthHolder, monthsBeforeCurrent: Int) {
        holder.bind(feelingCalendar[monthsBeforeCurrent])
    }

    override fun getItemCount() = feelingCalendar.size

    fun setFeelingCalendar(feelingCalendar: FeelingCalendar) {
        this.feelingCalendar = feelingCalendar
        notifyDataSetChanged()
    }
}
