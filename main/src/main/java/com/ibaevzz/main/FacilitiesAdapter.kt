package com.ibaevzz.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibaevzz.main.databinding.FacilityItemBinding

class FacilitiesAdapter(private val list: List<String>): RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>() {
    class ViewHolder(val binding: FacilityItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FacilityItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.facility.text = list[position]
    }
}