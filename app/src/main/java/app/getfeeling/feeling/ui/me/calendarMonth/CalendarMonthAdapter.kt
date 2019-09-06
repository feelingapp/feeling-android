package app.getfeeling.feeling.ui.me.calendarMonth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.ui.me.FeelingCalendar

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

    override fun getItemCount() = feelingCalendar.numOfMonths()

    fun setFeelingCalendar(feelingCalendar: FeelingCalendar) {
        this.feelingCalendar = feelingCalendar
        notifyDataSetChanged()
    }
}
