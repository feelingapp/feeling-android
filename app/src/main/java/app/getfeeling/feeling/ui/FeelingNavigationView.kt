package app.getfeeling.feeling.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

class FeelingNavigationView(context: Context, attrs: AttributeSet) :
    BottomNavigationView(context, attrs) {

    private var path: Path = Path()
    private var paint: Paint = Paint()

    private val curveCircleRadius: Float = 128 / 2f

    private var mFirstCurveStartPoint = PointF()
    private var mFirstCurveControlPoint1 = PointF()
    private var mFirstCurveControlPoint2 = PointF()
    private var mFirstCurveEndPoint = PointF()

    private var mSecondCurveStartPoint = PointF()
    private var mSecondCurveControlPoint1 = PointF()
    private var mSecondCurveControlPoint2 = PointF()
    private var mSecondCurveEndPoint = PointF()

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.WHITE
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // the coordinates (x,y) of the start point before curve
        mFirstCurveStartPoint.set(
            (width / 2) - (curveCircleRadius * 2) - (curveCircleRadius / 3),
            0f
        )
        // the coordinates (x,y) of the end point after curve
        mFirstCurveEndPoint.set(width / 2f, curveCircleRadius + (curveCircleRadius / 4))
        // same thing for the second curve
        mSecondCurveStartPoint = mFirstCurveEndPoint
        mSecondCurveEndPoint.set(
            (width / 2) + (curveCircleRadius * 2) + (curveCircleRadius / 3),
            0f
        )

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        mFirstCurveControlPoint1.set(
            mFirstCurveStartPoint.x + curveCircleRadius + (curveCircleRadius / 4),
            mFirstCurveStartPoint.y
        )
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        mFirstCurveControlPoint2.set(
            mFirstCurveEndPoint.x - (curveCircleRadius * 2) + curveCircleRadius,
            mFirstCurveEndPoint.y
        )

        mSecondCurveControlPoint1.set(
            mSecondCurveStartPoint.x + (curveCircleRadius * 2) - curveCircleRadius,
            mSecondCurveStartPoint.y
        )
        mSecondCurveControlPoint2.set(
            mSecondCurveEndPoint.x - (curveCircleRadius + (curveCircleRadius / 4)),
            mSecondCurveEndPoint.y
        )

        path.reset()
        path.moveTo(0f, 0f)
        path.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y)

        path.cubicTo(
            mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
            mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
            mFirstCurveEndPoint.x, mFirstCurveEndPoint.y
        )

        path.cubicTo(
            mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
            mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
            mSecondCurveEndPoint.x, mSecondCurveEndPoint.y
        )

        path.lineTo(width.toFloat(), 0f)
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
    }
}
