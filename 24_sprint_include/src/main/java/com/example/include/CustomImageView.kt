package com.example.include

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.graphics.drawable.toBitmap
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.min

class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var imageBitmap: Bitmap? = null
    private var rect: RectF = RectF(0f, 0f, 0f, 0f)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomImageView,
            defStyleAttr,
            defStyleRes,
        ).apply {
            try {
                imageBitmap = getDrawable(R.styleable.CustomImageView_imageResId)?.toBitmap()
            }
            finally {
                recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())

    }

    override fun onDraw(canvas: Canvas) {
        if (imageBitmap != null) {
            canvas.drawBitmap(imageBitmap!!, null, rect, null)
        }
    }

}