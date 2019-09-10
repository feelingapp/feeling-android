package app.getfeeling.feeling.ui.me.calendarMonth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarMonthBinding
import app.getfeeling.feeling.ui.me.FeelingCalendar
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter
import javax.inject.Inject

class CalendarMonthAdapter @Inject constructor(
    context: Context,
    private val calendarDayAdapter: AbstractCalendarDayAdapter
) : AbstractCalendarMonthAdapter() {

    private lateinit var feelingCalendar: FeelingCalendar

    private val months: Array<String> = context.resources.getStringArray(R.array.months)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarMonthHolder {
        val calendarMonthBinding = CalendarMonthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CalendarMonthHolder(calendarMonthBinding)
    }

    override fun onBindViewHolder(holder: CalendarMonthHolder, monthsBeforeCurrent: Int) {
        val feelingMonth = feelingCalendar[monthsBeforeCurrent]
        calendarDayAdapter.feelingMonth = feelingMonth
        holder.bind(calendarDayAdapter, months[feelingMonth.monthArrayValue])
    }

    override fun getItemCount() = feelingCalendar.numOfMonths()

    override fun setFeelingCalendar(feelingCalendar: FeelingCalendar) {
        this.feelingCalendar = feelingCalendar
        notifyDataSetChanged()
    }
}
