package app.getfeeling.feeling.ui.me.calendarDay

import android.widget.BaseAdapter
import app.getfeeling.feeling.ui.me.FeelingMonth

abstract class AbstractCalendarDayAdapter : BaseAdapter() {

    lateinit var feelingMonth: FeelingMonth
}
