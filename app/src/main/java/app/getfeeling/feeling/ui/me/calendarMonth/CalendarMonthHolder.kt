package app.getfeeling.feeling.ui.me.calendarMonth

import android.view.View
import android.widget.GridView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter

class CalendarMonthHolder(calendarMonth: View) : RecyclerView.ViewHolder(calendarMonth) {

    private val monthTextView: TextView = calendarMonth.findViewById(R.id.month)
    private val feelingGrid: GridView = calendarMonth.findViewById(R.id.feeling_grid)

    fun bind(calendarDayAdapter: AbstractCalendarDayAdapter, month: String) {
        monthTextView.text = month
        feelingGrid.adapter = calendarDayAdapter
    }
}
