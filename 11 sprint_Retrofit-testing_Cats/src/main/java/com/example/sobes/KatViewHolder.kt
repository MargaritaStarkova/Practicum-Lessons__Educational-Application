package com.example.sobes

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class KatViewHolder(parent: View): RecyclerView.ViewHolder(parent) {
    val image: ImageView = parent.findViewById(R.id.item)

    fun bind( catData: CatData) {
        Glide.with(itemView.context)
            .load(catData.url)
            .into(image)
    }

}