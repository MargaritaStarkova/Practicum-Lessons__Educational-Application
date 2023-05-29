package com.practicum.testapp

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import com.bumptech.glide.Glide

import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val imageUrl = "https://img.freepik.com/free-vector/open-blue-book-white_1308-69339.jpg"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<ImageView>(R.id.image)

        Glide.with(this).load(imageUrl).into(image)




    }
}