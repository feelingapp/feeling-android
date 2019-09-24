package app.getfeeling.feeling.ui.me.calendarDay

import android.widget.BaseAdapter
import app.getfeeling.feeling.ui.me.FeelingMonth

abstract class AbstractCalendarDayAdapter : BaseAdapter() {

    internal lateinit var feelingMonth: FeelingMonth

    fun setFeelingMonth(feelingMonth: FeelingMonth) {
        this.feelingMonth = feelingMonth
        notifyDataSetChanged()
    }
}
