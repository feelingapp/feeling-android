package app.getfeeling.feeling.ui.me.calendarMonth

import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.ui.me.FeelingCalendar

abstract class AbstractCalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthHolder>() {

    internal var feelingCalendar: FeelingCalendar? = null

    lateinit var listener: AdapterView.OnItemClickListener

    fun setFeelingCalendar(feelingCalendar: FeelingCalendar) {
        this.feelingCalendar = feelingCalendar
        notifyDataSetChanged()
    }
}
