package com.example.test_app_kotlin

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var relativeLayout: RelativeLayout? = null

    private var lastButtonIndex = 0
    private var lastButtonId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout = findViewById(R.id.relative_layout)
        findViewById<View>(R.id.add_button)?.setOnClickListener {
            addButton()
        }
    }

    private fun addButton() {
        // Создаём кнопку
        val newButton = createNewButton(lastButtonIndex, lastButtonId)

        // Добавляем кнопку в контейнер
        relativeLayout?.addView(newButton)

        // Обновляем значения для следующих использований
        ++lastButtonIndex
        lastButtonId = newButton.id
    }

    private fun createNewButton(
        currentButtonIndex: Int,
        previousButtonId: Int,
    ): View {
        val isFirstButton = currentButtonIndex == 0

        return Button(this).also { button ->
            button.text = "Button # $lastButtonIndex"

            // идентификатор нам нужен, чтобы привязываться к кнопкам.
            button.id = View.generateViewId()

            button.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
            ).apply {
                this.marginEnd = 10
                if (isFirstButton) {
                    // Если у нас первая кнопка — привязываем её к родительскому контейнеру
                    this.addRule(RelativeLayout.ALIGN_PARENT_END)
                } else {
                    // Если кнопка не первая — привязываем её к предыдущей кнопке
                    this.addRule(RelativeLayout.START_OF, previousButtonId)
                }
            }
        }
    }

}
