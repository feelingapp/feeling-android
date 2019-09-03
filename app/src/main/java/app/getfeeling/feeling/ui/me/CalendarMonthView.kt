package app.getfeeling.feeling.ui.me

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView

class CalendarMonthView(context: Context, attrs: AttributeSet) : GridView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // wrap_content doesn't work for gridview for some reason so here's a hacky fix

        // The two leftmost bits in the height measure spec have
        // a special meaning, hence we can't use them to describe height.
        val heightSpec = MeasureSpec.makeMeasureSpec(
            Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST
        )

        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}
