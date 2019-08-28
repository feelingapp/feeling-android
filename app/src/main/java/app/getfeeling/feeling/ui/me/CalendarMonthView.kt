package app.getfeeling.feeling.ui.me

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import android.widget.TextView

class CalendarMonthView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    lateinit var month: TextView
    lateinit var feelingsGrid: GridView
}
