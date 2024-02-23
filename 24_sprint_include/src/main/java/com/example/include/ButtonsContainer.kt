package com.example.include

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.Surface
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.view.setPadding

internal class ButtonsContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.buttonsContainerStyle,
    @StyleRes defStyleRes: Int = R.style.DefaultButtonsContainerStyle,
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.buttons_container, this, true)

        when(this.resources.configuration.orientation) {
            ORIENTATION_LANDSCAPE -> {
                this.orientation = LinearLayout.HORIZONTAL
                this.setPadding(resources.getDimensionPixelSize(R.dimen.dp16))
            }
            else -> {
                this.orientation = LinearLayout.VERTICAL
                this.setPadding(resources.getDimensionPixelSize(R.dimen.dp16))
            }
        }



        val topButton = findViewById<Button>(R.id.top_button)
        val bottomButton = findViewById<Button>(R.id.bottom_button)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ButtonsContainer,
            defStyleAttr,
            defStyleRes
        ).apply {
            try {
                val topButtonText = getString(R.styleable.ButtonsContainer_topButtonText) ?: ""
                val bottomButtonText = getString(R.styleable.ButtonsContainer_bottomButtonText)
                    ?: ""
                val bottomButtonBackgroundColor = getColor(R.styleable.ButtonsContainer_bottomButtonBackgroundColor, 0)
                val topButtonBackgroundColor = getColor(R.styleable
                    .ButtonsContainer_topButtonBackgroundColor, 0)

                topButton.text = topButtonText
                bottomButton.text = bottomButtonText
                bottomButton.setBackgroundColor(bottomButtonBackgroundColor)
                topButton.setBackgroundColor(topButtonBackgroundColor)

            } finally {
                recycle()
            }
        }
    }

}