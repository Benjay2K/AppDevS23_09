package com.example.appdevs23_09

import android.content.Context
import android.content.res.Configuration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BubbleLevelView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val bubblePaint: Paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private var bubbleX: Float = 0f
    private var bubbleY: Float = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = width / 10f
        canvas.drawCircle(bubbleX, bubbleY, radius, bubblePaint)
    }

    fun updateBubblePosition(x: Float, y: Float) {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape orientation, restrict movement to the horizontal axis
            bubbleX = x.coerceIn(0f, height.toFloat())
            bubbleY = width / 2f
        } else {
            // Portrait orientation, restrict movement to the horizontal axis
            bubbleX = x.coerceIn(0f, height.toFloat())
            bubbleY = width / 2f
        }
        invalidate()
    }
}