package app.getfeeling.feeling.ui.me.calendarMonth

import android.widget.AdapterView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import app.getfeeling.feeling.ui.me.FeelingMonth

abstract class AbstractCalendarMonthAdapter :
    PagedListAdapter<FeelingMonth, CalendarMonthHolder>(DIFF_CALLBACK) {

    lateinit var listener: AdapterView.OnItemClickListener

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FeelingMonth> =
            object : DiffUtil.ItemCallback<FeelingMonth>() {
                override fun areItemsTheSame(
                    oldItem: FeelingMonth,
                    newItem: FeelingMonth
                ): Boolean {
                    return oldItem.yearMonth === newItem.yearMonth
                }

                override fun areContentsTheSame(
                    oldItem: FeelingMonth,
                    newItem: FeelingMonth
                ): Boolean {
                    if (oldItem.feelings.size != newItem.feelings.size) return false

                    for (i in 0..oldItem.feelings.size) {
                        if (oldItem.feelings[i] != newItem.feelings[i]) return false
                    }

                    return true
                }
            }
    }
}
