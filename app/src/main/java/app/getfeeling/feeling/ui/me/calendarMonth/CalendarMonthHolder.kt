package app.getfeeling.feeling.ui.me.calendarMonth

import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.databinding.CalendarMonthBinding
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter

class CalendarMonthHolder(private val binding: CalendarMonthBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(calendarDayAdapter: AbstractCalendarDayAdapter, month: String) {
        binding.month.text = month
        binding.feelingGrid.adapter = calendarDayAdapter
    }
}
