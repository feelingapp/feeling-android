package app.getfeeling.feeling.ui.me

import android.view.View
import android.widget.GridView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.room.entities.Feeling

class CalendarMonthHolder(calendarMonth: View, private val months: Array<String>) :
    RecyclerView.ViewHolder(calendarMonth) {

    private val monthTextView: TextView = calendarMonth.findViewById(R.id.month)
    private val feelingGrid: GridView = calendarMonth.findViewById(R.id.feeling_grid)

    fun bind(monthFeelings: List<Feeling>) {
        if (monthFeelings.isNotEmpty()) {
            monthTextView.text = months[monthFeelings[0].createdAt.monthValue - 1]

            val feelingMonthAdapter = CalendarDayAdapter()
            feelingMonthAdapter.monthFeelings = monthFeelings

            feelingGrid.adapter = feelingMonthAdapter
        }
    }
}
