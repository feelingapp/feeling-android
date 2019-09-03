package app.getfeeling.feeling.ui.me

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.getfeeling.feeling.R
import app.getfeeling.feeling.room.entities.Feeling

class CalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthHolder>() {

    lateinit var months: Array<String>

    var allFeelings: List<List<Feeling>> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarMonthHolder {
        val calendarMonth = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_month, parent, false)

        return CalendarMonthHolder(calendarMonth, months)
    }

    override fun onBindViewHolder(holder: CalendarMonthHolder, monthsBeforeCurrent: Int) {
        holder.bind(allFeelings[monthsBeforeCurrent])
    }

    override fun getItemCount() = allFeelings.size
}
