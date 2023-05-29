package com.practicum.testingrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val colors = listOf(R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5,
            R.color.color6, R.color.color7, R.color.color8, R.color.color9, R.color.color10)

        val recyclerView = findViewById<RecyclerView>(R.id.colorsList)
        recyclerView.adapter = ColorsAdapter(
            colors = List(1000) {
                Color(colors[it % 10])
            }
        )
        recyclerView.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)

    }
}