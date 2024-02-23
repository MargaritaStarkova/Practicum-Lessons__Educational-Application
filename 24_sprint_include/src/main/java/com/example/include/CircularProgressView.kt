package com.example.include

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import kotlin.math.min

class CircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var maxProgress = 100f

    private var currentProgress = 35f

    fun setCurrentProgress(newCurrentProgress: Float) {
        // Проверяем, валидно ли новое значение прогресса
        if (newCurrentProgress < 0f || newCurrentProgress > 100f) return

        this.currentProgress = newCurrentProgress

        // Явно указываем виджету на необходимость перерисовки
        invalidate()
    }


    // region Размеры
    private val minViewSize = resources.getDimensionPixelSize(R.dimen.circularProgressViewMinSize)

    // Ширина полоски фона и прогресса
    private val strokeWidthPx = 20f
    // endregion

    // region Кисти
    // Создаём кисть для отрисовки фоновой дорожки
    private val trackPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.GRAY
        strokeWidth = strokeWidthPx
    }

    // Создаём кисть для отрисовки прогресса
    private val progressPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = strokeWidthPx
    }
    // endregion

    // region Расчёты
    // Рассчитываем координаты центра круга
    private var centerX = 0f
    private var centerY = 0f

    // Рассчитываем радиус круга
    private var radius = 0f

    private val arcRect = RectF()
    // endregion

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Расчёт ширины
        val withSize = MeasureSpec.getSize(widthMeasureSpec)
        val contentWith = when (val withMode = MeasureSpec.getMode(widthMeasureSpec)) {
            // Если ограничений на ширину нет —
            // берём минимальное значение
            MeasureSpec.UNSPECIFIED -> minViewSize

            // Если нужно указать точное значение ширины —
            // берём это значение
            MeasureSpec.EXACTLY -> withSize

            // Если можно указать не более widthSize —
            // берём максимальную возможную ширину
            MeasureSpec.AT_MOST -> withSize
            else -> error("Неизвестный режим ширины ($withMode)")
        }

        // Расчёт высоты
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val contentHeight = when (val heightMode = MeasureSpec.getMode(heightMeasureSpec)) {
            // Если ограничений на высоту нет —
            // берём минимальное значение
            MeasureSpec.UNSPECIFIED -> minViewSize

            // Если нужно указать точное значение высоты —
            // берём это значение
            MeasureSpec.EXACTLY -> heightSize

            // Если можно указать не более heightSize —
            // берём максимальную возможную высоту
            MeasureSpec.AT_MOST -> heightSize
            else -> error("Неизвестный режим высоты ($heightMode)")
        }

        // Берём минимальное значение — либо ширину, либо высоту,
        // чтобы сформировать квадрат.
        val size = min(contentWith, contentHeight)

        // Устанавливаем посчитанные размеры
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Рассчитываем центр и радиус с учётом новых размеров
        centerX = measuredWidth / 2f
        centerY = measuredHeight / 2f

        // Рассчитываем радиус круга
        radius = (measuredWidth - strokeWidthPx) / 2f

        // устанавливаем координаты прямоугольника для
        // отрисовки дуги прогресса
        arcRect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    override fun onDraw(canvas: Canvas) = with(canvas) {
        drawTrack(centerX, centerY, radius)
        drawProgress()
    }

    // Логика отрисовки фона
    private fun Canvas.drawTrack(centerX: Float, centerY: Float, radius: Float) {
        // Рисуем фоновую дорожку
        drawCircle(centerX, centerY, radius, trackPaint)
    }

    // Логика отрисовки прогресса
    private fun Canvas.drawProgress() {
        // Рисуем индикатор прогресса


        // Определяем угол дуги, которая обозначает текущий прогресс.
        // currentProgress / maxProgress — это отношение текущего прогресса к максимальному.
        //  Оно даёт долю от 0 до 1, которая представляет собой процент завершения задачи или процесса
        // * 360: Поскольку полный круг соответствует 360 градусам,
        // умножение доли на 360 преобразует её в угол дуги.
        val sweepAngle = (currentProgress / maxProgress) * 360


        drawArc(arcRect, -90f, sweepAngle, false, progressPaint)
    }
}