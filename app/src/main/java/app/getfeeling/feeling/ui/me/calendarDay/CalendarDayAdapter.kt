package app.getfeeling.feeling.ui.me.calendarDay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.getfeeling.feeling.databinding.CalendarDayBinding

class CalendarDayAdapter : AbstractCalendarDayAdapter() {

    override fun isEnabled(position: Int): Boolean =
        position >= feelingMonth.dayOffset && feelingMonth.getWithOffset(position) != null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var calendarDayView = convertView
        val viewHolder: CalendarDayHolder

        if (calendarDayView == null) {
            val binding = CalendarDayBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )

            viewHolder = CalendarDayHolder(parent!!.context, binding)
            calendarDayView = binding.root
            calendarDayView.tag = viewHolder
        } else {
            viewHolder = calendarDayView.tag as CalendarDayHolder
        }

        viewHolder.bind(position, feelingMonth)

        return calendarDayView
    }

    override fun getCount(): Int = feelingMonth.monthLength + feelingMonth.dayOffset

    // Required override but no need for implementation
    override fun getItem(position: Int): Any = throw NotImplementedError()

    // No Id needed as stored in holder
    override fun getItemId(position: Int): Long = 0
}

