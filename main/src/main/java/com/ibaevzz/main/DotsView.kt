package com.ibaevzz.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.min

@SuppressLint("Recycle")
class DotsView(context: Context, attrs: AttributeSet?): View(context, attrs){

    private var dots = 0
    private var dotsNumber = 1
    private var dotsRadius = 10f
    private var dotsCount = 5
    private var corner = 20f

    private val background = "#ffffff"
    private val dotColors = listOf("#000000", "#38000000", "#2b000000", "#1A000000", "#0d000000")

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DotsView, 0, 0)
        dotsCount = typedArray.getInt(R.styleable.DotsView_dots_count, 5)
        dotsRadius = typedArray.getDimension(R.styleable.DotsView_dots_radius, 10f)
        corner = typedArray.getDimension(R.styleable.DotsView_corner, 20f)
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

        setMeasuredDimension(width as Int, height as Int)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(dots==0){
            return
        }

        val paintRect  = Paint()
        paintRect.color = Color.parseColor(background)
        paintRect.style = Paint.Style.FILL
        val paintDot = Paint()
        paintDot.style = Paint.Style.FILL

        canvas.drawRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()),
            corner.toFloat(), corner.toFloat(), paintRect)

        val sumRad = 2*dotsRadius*dotsCount
        val space = (width-sumRad)/(dotsCount+1)
        val y = height/2
        if(dots in 1..dotsCount){
            for(i in 1..dotsCount){
                val x = i*space+(1+2*(i-1))*dotsRadius

                paintDot.color = Color.parseColor(dotColors[abs(dotsNumber-i)])
                canvas.drawCircle(x, y.toFloat(), dotsRadius, paintDot)
            }
        }else{
            val positionLeft = dots - dotsNumber + 1
            for(i in 1..dotsCount){
                val x = i*space+(1+2*(i-1))*dotsRadius

                if(positionLeft<=dotsCount/2+1){
                    val colorIndex = abs(6-i-positionLeft)
                    if(colorIndex<=4)
                        paintDot.color = Color.parseColor(dotColors[colorIndex])
                    else
                        paintDot.color = Color.parseColor(dotColors[4])
                    canvas.drawCircle(x, y.toFloat(), dotsRadius, paintDot)
                }else if(dotsNumber<=dotsCount/2+1){
                    val colorIndex = abs(dotsNumber-i)
                    if(colorIndex<=4)
                        paintDot.color = Color.parseColor(dotColors[colorIndex])
                    else
                        paintDot.color = Color.parseColor(dotColors[4])
                    canvas.drawCircle(x, y.toFloat(), dotsRadius, paintDot)
                }
            }
        }
    }

    fun addSlider(slider: ViewPager2){
        dots = slider.adapter?.itemCount!!
        dotsCount = min(dotsCount, dots)
        if(dotsCount!=dots&&dotsCount%2==0){
            dotsCount+=1
        }
        slider.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotsNumber = position+1
                invalidate()
            }
        })
        dotsNumber = 1
        invalidate()
    }
}