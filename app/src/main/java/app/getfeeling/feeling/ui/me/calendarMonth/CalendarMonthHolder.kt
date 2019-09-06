package app.getfeeling.feeling.ui.me.calendarMonth

import android.view.View
import android.widget.GridView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.ui.me.FeelingMonth
import app.getfeeling.feeling.ui.me.calendarDay.CalendarDayAdapter

class CalendarMonthHolder(calendarMonth: View, private val months: Array<String>) :
    RecyclerView.ViewHolder(calendarMonth) {

    private val monthTextView: TextView = calendarMonth.findViewById(R.id.month)
    private val feelingGrid: GridView = calendarMonth.findViewById(R.id.feeling_grid)

    fun bind(feelingMonth: FeelingMonth) {
        monthTextView.text = months[feelingMonth.monthArrayValue]
        feelingGrid.adapter =
            CalendarDayAdapter(feelingMonth)
    }
}
