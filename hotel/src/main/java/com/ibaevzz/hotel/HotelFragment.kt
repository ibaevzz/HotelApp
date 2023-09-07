package com.ibaevzz.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ibaevzz.hotel.databinding.FragmentHotelBinding

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater)
        return binding.root
    }
}