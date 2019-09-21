package app.getfeeling.feeling.ui.me.calendarDay

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class CalendarDayView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // To make sure grid cell is square
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}
