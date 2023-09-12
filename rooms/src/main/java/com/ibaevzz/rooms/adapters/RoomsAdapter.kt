package com.ibaevzz.rooms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.ibaevzz.main.FacilitiesAdapter
import com.ibaevzz.main.SliderAdapter
import com.ibaevzz.rooms.RoomModel
import com.ibaevzz.rooms.databinding.RoomLayoutBinding

class RoomsAdapter(private val rooms: List<RoomModel>, private val callback: ()->Unit): RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>(){
    class RoomViewHolder(val binding: RoomLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = RoomLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.binding.apply {
            photoSlider.adapter = SliderAdapter(room.imageUrls)
            roomName.text = room.name
            price.text = room.price.toString()
            priceType.text = room.priceType
            facilities.layoutManager = FlexboxLayoutManager(root.context)
            facilities.adapter = FacilitiesAdapter(room.peculiarities)
            selectRoom.setOnClickListener{
                callback()
            }
        }
    }

    override fun getItemCount(): Int {
        return rooms.size
    }
}