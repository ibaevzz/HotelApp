package com.ibaevzz.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

@SuppressLint("Recycle")
class DotsView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.attrs = attrs
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr){
        this.attrs = attrs
        styleAttr = defStyleAttr
    }

    private var attrs: AttributeSet? = null
    private var styleAttr: Int = 0

    private var dots = 0
    private var dotsNumber = 0
    private var dotsRadius = 10
    private var dotsCount = 5
    private var corner = 20

    private val background = 0xffffff
    private val dotColors = listOf(0x000000, 0x38000000, 0x2b000000, 0x1A000000, 0x0d000000)

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DotsView, styleAttr, 0)
        dotsCount = typedArray.getInt(R.styleable.DotsView_dots_count, 5)
        dotsRadius = typedArray.getDimension(R.styleable.DotsView_dots_radius, 10f).toInt()
        corner = typedArray.getDimension(R.styleable.DotsView_corner, 20f).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = dotsRadius*dotsCount*2
        val desiredHeight = dotsRadius*2

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> if(desiredWidth<widthSize) desiredWidth else widthSize
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> if(desiredHeight<heightSize) desiredHeight else heightSize
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(dots==0){
            return
        }
        val paintRect  = Paint()
        paintRect.color = background
        paintRect.style = Paint.Style.FILL
        val paintDot = Paint()
        paintDot.style = Paint.Style.FILL

        canvas.drawRoundRect(RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat()),
            corner.toFloat(), corner.toFloat(), paintRect)

        if(dots in 1..dotsCount){
            val sumRad = dots*dotsRadius
            val space = (width-sumRad)/(dots+1)
            for(i in 1..dots){
                val x = left+i*space+(i+2*(i-1))*dotsRadius
                val y = top+height/2

                paintDot.color = dotColors[abs(dotsNumber-i)]
                canvas.drawCircle(x.toFloat(), y.toFloat(), dotsRadius.toFloat(), paintDot)
            }
        }else{
            val sumRad = dotsRadius*5
            val space = (width-sumRad)/6
            val positionLeft = dots - dotsNumber + 1
            for(i in 1..dotsCount){
                val x = left+i*space+(i+2*(i-1))*dotsRadius
                val y = top+height/2

                if(positionLeft<=dotsCount/2+1){
                    paintDot.color = dotColors[abs(6-i-positionLeft)]
                    canvas.drawCircle(x.toFloat(), y.toFloat(), dotsRadius.toFloat(), paintDot)
                }else if(dotsNumber<=dotsCount/2+1){
                    paintDot.color = dotColors[abs(dotsNumber-i)]
                    canvas.drawCircle(x.toFloat(), y.toFloat(), dotsRadius.toFloat(), paintDot)
                }
            }
        }
    }

    fun addSlider(slider: ViewPager2){
        dots = slider.currentItem
        slider.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotsNumber = position
                invalidate()
            }
        })
        dotsNumber = 1
        invalidate()
    }
}