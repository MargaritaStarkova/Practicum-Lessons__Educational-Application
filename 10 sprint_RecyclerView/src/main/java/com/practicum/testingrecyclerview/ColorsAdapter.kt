package com.practicum.testingrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter (
    private val colors: List<Color>
) : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_view, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount() = colors.size

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(color: Color) {
            itemView.setBackgroundColor(itemView.context.getColor(color.value))
        }
    }
}