package com.va.kotlintaste.multiShape

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View

/**
 * Created by Junmeng.Chen on 2017/9/13.
 */
class RadarView : View {

    var paint: Paint? = null

    var grayPaint: Paint? = null

    var linePaint: Paint? = null

    var fillPaint: Paint? = null

    val multi = 5

    private var centerX: Float = 0F

    private var centerY: Float = 0F

    private var radius: Float = 0F

    private var paths: List<Path>? = null

    private var linePath: Path? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet!!, 0)

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init()
    }

    private fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.color = Color.BLACK
        paint!!.style = Paint.Style.STROKE
        paint?.strokeWidth = 1F
        paint!!.pathEffect = DashPathEffect(floatArrayOf(4.0F, 4.0F), 0F)

        grayPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        grayPaint?.color = Color.GRAY

        fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        fillPaint?.color = Color.CYAN
        fillPaint?.style = Paint.Style.FILL

        linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        linePaint?.color = Color.RED
        linePaint?.style = Paint.Style.STROKE
        linePaint?.strokeWidth = 5F

        linePath = Path()

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = (measuredWidth / 2).toFloat()
        centerY = (measuredHeight / 2).toFloat()
        radius = (measuredWidth / 4).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.i("cjm", "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.i("cjm", "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        Log.i("cjm", "onDraw")

        val angle: Float = (360 / multi).toFloat()

        canvas?.translate(centerX, centerY)

        var tempRadius = radius

        for (index in 0..4) {
            canvas?.drawCircle(0F, 0F, tempRadius, paint)
            tempRadius -= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics)
        }

        canvas?.drawPoint(0F, 0F, paint)

        canvas?.rotate(-90F, 0F, 0F)

        linePath?.reset()

        linePath?.moveTo(radius, 0F)

        for (index in 1 until multi) {
            var nextInt = Math.random()
            var temp = radius * nextInt
            var pointF = PointF()
            pointF.x = (temp * Math.cos(angleToRadian(index * angle))).toFloat()
            pointF.y = (temp * Math.sin(angleToRadian(index * angle))).toFloat()
            linePath?.lineTo(pointF.x, pointF.y)
            textCenter("abc", grayPaint, canvas, pointF, Paint.Align.RIGHT)
        }

        linePath?.close()

        canvas?.drawPath(linePath, linePaint)

        canvas?.drawPath(linePath, fillPaint)
    }

    private fun angleToRadian(angle: Float): Double {
        return angle * Math.PI / 180
    }

    private fun textCenter(string: String, paint: Paint?, canvas: Canvas?, pointF: PointF, align: Paint.Align) {

        paint?.textAlign = align
        var fontMetrics = paint?.fontMetrics
        var top = fontMetrics?.top
        var bottom = fontMetrics?.bottom
        var length = string.length
        var total = (length - 1) * (-top!! + bottom!!) + (-fontMetrics?.ascent!! + fontMetrics?.descent)
        var offset = total / 2 - bottom
        (0 until length)
                .map { -(length - it - 1) * (-top + bottom) + offset }
                .forEach { canvas?.drawText(string, pointF.x, pointF.y + it, paint) }

    }

}