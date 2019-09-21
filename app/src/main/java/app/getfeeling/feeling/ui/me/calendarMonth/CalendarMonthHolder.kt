package app.getfeeling.feeling.ui.me.calendarMonth

import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.databinding.CalendarMonthBinding
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter

class CalendarMonthHolder(private val binding: CalendarMonthBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        calendarDayAdapter: AbstractCalendarDayAdapter,
        monthText: String,
        listener: AdapterView.OnItemClickListener
    ) {
        with(binding) {
            month.text = monthText
            feelingGrid.adapter = calendarDayAdapter
            feelingGrid.onItemClickListener = listener
        }
    }
}
