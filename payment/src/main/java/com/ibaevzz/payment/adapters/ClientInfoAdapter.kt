package com.ibaevzz.payment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ibaevzz.payment.databinding.AddTouristBinding
import com.ibaevzz.payment.databinding.ClientInfoBinding
import com.ibaevzz.payment.databinding.ClientInfoFullBinding

object Constants {
    const val FULL = 0
    const val SMALL = 1
    const val ADD = 2
    val id = listOf("Первый", "Второй", "Третий", "Четвертый",
            "Пятый", "Шестой", "Седьмой", "Восьмой", "Девятый", "Десятый")
}

class ClientInfoAdapter(private val listType: MutableList<Int>): RecyclerView.Adapter<ViewHolder>() {

    class FullClientInfoViewHolder(val binding: ClientInfoFullBinding): ViewHolder(binding.root)
    class ClientInfoViewHolder(val binding: ClientInfoBinding): ViewHolder(binding.root)
    class AddViewHolder(val binding: AddTouristBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var holder: ViewHolder? = null
        when (viewType) {
            Constants.FULL -> {
                val binding = ClientInfoFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                holder = FullClientInfoViewHolder(binding)
            }
            Constants.SMALL -> {
                val binding = ClientInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                holder = ClientInfoViewHolder(binding)
            }
            Constants.ADD -> {
                val binding = AddTouristBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                holder = AddViewHolder(binding)
            }
        }
        return holder!!
    }

    override fun getItemCount(): Int {
        return listType.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (listType[position]) {
            Constants.FULL -> {
                val binding = (holder as FullClientInfoViewHolder).binding
                binding.clientId.text = Constants.id[position]+" турист"
                binding.up.setOnClickListener{
                    listType[position] = Constants.SMALL
                    notifyDataSetChanged()
                }
            }
            Constants.SMALL -> {
                val binding = (holder as ClientInfoViewHolder).binding
                binding.clientId.text = Constants.id[position]+" турист"
                binding.down.setOnClickListener{
                    listType[position] = Constants.FULL
                    notifyDataSetChanged()
                }
            }
            Constants.ADD -> {
                val binding = (holder as AddViewHolder).binding
                binding.add.setOnClickListener{
                    if(listType.size<10) {
                        listType[position] = Constants.SMALL
                        listType.add(Constants.ADD)
                    }else{
                        listType[position] = Constants.SMALL
                    }
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listType[position]
    }
}