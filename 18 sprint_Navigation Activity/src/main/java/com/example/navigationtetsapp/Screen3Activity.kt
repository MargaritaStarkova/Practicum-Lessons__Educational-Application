package com.example.navigationtetsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb_api.R

class Screen3Activity : AppCompatActivity(R.layout.activity_screen3) {
    
    // Описали callback для обработки нажатия на Back
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showWarningToast()
            disableCallback()
        }
        
    }
    
    // Создали Handler для работы с postDelay
    private val handler = Handler(Looper.getMainLooper())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        Log.d("NAVEXAMPLE", "Screen3 -> onCreate")
        
        findViewById<TextView>(R.id.screen2Title).text = "Screen 3 [$this]"
        findViewById<Button>(R.id.screen3ButtonToScreen1).setOnClickListener {
            Log.d("NAVEXAMPLE", "Screen -> Click on 'To screen 1'")
            openScreen1()
        }
        
        findViewById<Button>(R.id.screen3ButtonBack).setOnClickListener {
            Log.d("NAVEXAMPLE", "Screen3 -> Click on 'Back'")
            backToPreviousScreen()
        }
        
        findViewById<Button>(R.id.screen3ButtonOpenScreen3).setOnClickListener {
            Log.d("NAVEXAMPLE", "Screen -> Click on 'Open Screen 3'")
            openScreen3()
        }
        
        findViewById<Button>(R.id.screen3ButtonToScreen1WithClear).setOnClickListener {
            Log.d("NAVEXAMPLE", "Screen -> Click on 'Back to Screen 1'")
            backToScreen1()
        }
        
        // Добавляем созданный callback к Dispatcher
        onBackPressedDispatcher.addCallback(this, callback)
        
    }
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        
        Log.d("NAVEXAMPLE", "Screen3 -> onNewIntent")
    }
    
    private fun backToScreen1() {
        val intent = Intent(this, Screen1Activity::class.java)
        this.startActivity(intent)
    }
    
    private fun disableCallback() {
        // Отключаем callback
        callback.isEnabled = false
        // И через две секунды включаем его обратно
        handler.postDelayed({ callback.isEnabled = true }, 2000L)
    }
    
    // Показываем Toast с предупреждением
    private fun showWarningToast() {
        Toast
            .makeText(
                this@Screen3Activity,
                "Нажмите еще раз, чтобы перейти на предыдущий экран",
                Toast.LENGTH_SHORT
            )
            .show()
    }
    
    private fun backToPreviousScreen() {
        this.onBackPressedDispatcher.onBackPressed()
    }
    
    private fun openScreen1() {
        val intent = Intent(this, Screen1Activity::class.java)
        this.startActivity(intent)
    }
    
    private fun openScreen3() {
        val intent = Intent(this, Screen3Activity::class.java)
        this.startActivity(intent)
    }
}
