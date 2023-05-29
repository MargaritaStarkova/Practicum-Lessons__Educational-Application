package com.example.audioplayer.ui.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.audioplayer.databinding.RecyclerItemBinding

class TrackViewHolder(
    private val binding: RecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    //for example
    fun bind(item: Any) {
       /*  binding.title.text = item.text
        binding.field.text = item.field */
    }
    
}
