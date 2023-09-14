package com.ibaevzz.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ibaevzz.success.databinding.SuccessFragmentBinding

class SuccessFragment: Fragment() {

    private lateinit var binding: SuccessFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Заказ оплачен"
            setHomeAsUpIndicator(com.ibaevzz.main.R.drawable.arrow_back)
        }
        binding = SuccessFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cool.setOnClickListener{
            findNavController().navigate(com.ibaevzz.main.R.id.action_successFragment_to_hotelFragment)
        }
    }

}