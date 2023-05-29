package com.example.audioplayer.ui.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.audioplayer.databinding.RecyclerItemBinding

class RecyclerViewAdapter: RecyclerView.Adapter<TrackViewHolder>()  {
    val list = ArrayList<Any>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TrackViewHolder {
        return TrackViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val trackItem = list[holder.adapterPosition]
        holder.bind(item = trackItem)
     
    }

    override fun getItemCount(): Int {
        return list.size
    }

}