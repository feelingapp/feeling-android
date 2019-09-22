package app.getfeeling.feeling.ui.me.calendarDay

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.getfeeling.feeling.databinding.CalendarDayBinding
import app.getfeeling.feeling.valueobjects.Feeling

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

    override fun getItem(position: Int): Feeling? = feelingMonth.getWithOffset(position)

    override fun getItemId(position: Int): Long {
        if (position < feelingMonth.dayOffset || position > feelingMonth.dayOffset + feelingMonth.monthLength) {
            Log.d("ItemId", "$position: -1")
            return -1
        }

        val feeling = feelingMonth.getWithOffset(position)
        if (feeling != null) {
            Log.d("ItemId", "$position: ${feeling.id}")
            return feeling.id.toLong()
        }

        Log.d("ItemId", "$position: -1")
        return -1
    }

    override fun getCount(): Int = feelingMonth.monthLength + feelingMonth.dayOffset
}

