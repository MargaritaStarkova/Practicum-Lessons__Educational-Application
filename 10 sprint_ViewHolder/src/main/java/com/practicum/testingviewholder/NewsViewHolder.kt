package com.practicum.testingviewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsViewHolder(parentView: ViewGroup) : RecyclerView.ViewHolder(parentView) {

    private val sourceName: TextView
    private val text: TextView

    init {
        val itemView = LayoutInflater.from(parentView.context).inflate(R.layout.news_view, parentView, false)
        sourceName = itemView.findViewById(R.id.sourceName)
        text = itemView.findViewById(R.id.text)
    }


    fun bind(model: News) {
        // присваиваем в TextView значения из нашей модели
        sourceName.text = model.name
        text.text = model.content
    }
}