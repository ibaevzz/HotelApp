package com.ibaevzz.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ibaevzz.main.R
import com.ibaevzz.main.databinding.SliderItemBinding
import com.squareup.picasso.Picasso

class SliderAdapter(private val list: List<String>): RecyclerView.Adapter<SliderAdapter.ImageViewHolder>() {
    class ImageViewHolder(val binding: SliderItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(SliderItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(list[position]).error(R.drawable.image_not_found).into(holder.binding.image)
    }
}