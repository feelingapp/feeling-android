package app.getfeeling.feeling.ui.me.calendarMonth

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import app.getfeeling.feeling.R
import app.getfeeling.feeling.databinding.CalendarMonthBinding
import app.getfeeling.feeling.ui.me.calendarDay.AbstractCalendarDayAdapter
import org.threeten.bp.Year
import javax.inject.Inject
import javax.inject.Provider

class CalendarMonthAdapter @Inject constructor(
    context: Context,
    private val calendarDayAdapterProvider: Provider<AbstractCalendarDayAdapter>
) : AbstractCalendarMonthAdapter() {

    private val months: Array<String> = context.resources.getStringArray(R.array.months)

    private val currentYear = Year.now().value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarMonthHolder {
        val calendarMonthBinding = CalendarMonthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CalendarMonthHolder(calendarMonthBinding)
    }

    override fun onBindViewHolder(holder: CalendarMonthHolder, monthsBeforeCurrent: Int) {
        val feelingMonth = getItem(monthsBeforeCurrent)!!
        val calendarDayAdapter = calendarDayAdapterProvider.get().apply {
            setFeelingMonth(feelingMonth)
        }

        val month = months[feelingMonth.monthArrayValue]
        val headerText =
            if (currentYear == feelingMonth.year) month else "$month ${feelingMonth.year}"
        holder.bind(
            calendarDayAdapter,
            headerText,
            listener
        )
    }
}
