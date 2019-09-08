package app.getfeeling.feeling.ui.me.calendarMonth

import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.ui.me.FeelingCalendar

abstract class AbstractCalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthHolder>() {

    abstract fun setFeelingCalendar(feelingCalendar: FeelingCalendar)
}
