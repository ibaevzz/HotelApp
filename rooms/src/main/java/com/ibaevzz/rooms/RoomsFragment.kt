package com.ibaevzz.rooms

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibaevzz.main.ViewModelFactory
import com.ibaevzz.rooms.adapters.RoomsAdapter
import com.ibaevzz.rooms.databinding.FragmentRoomsBinding
import com.ibaevzz.rooms.di.RoomsComponentProvider
import javax.inject.Inject

class RoomsFragment: Fragment() {

    private lateinit var binding: FragmentRoomsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RoomsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as RoomsComponentProvider).provideRooms().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = requireArguments().getString("hotel_name")
            setHomeAsUpIndicator(com.ibaevzz.main.R.drawable.arrow_back)
        }

        binding = FragmentRoomsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.root.setOnRefreshListener {
            viewModel.updateRoomsModel()
        }
        binding.rooms.layoutManager = LinearLayoutManager(context)
        viewModel.roomsModel.observe(this){
            setView(it)
        }
    }

    private fun setView(roomsModel: RoomsModel){
        binding.apply {
            root.isRefreshing = false
            progress.visibility = View.GONE
            rooms.visibility = View.VISIBLE
            rooms.adapter = RoomsAdapter(roomsModel.rooms){
                findNavController().navigate(com.ibaevzz.main.R.id.action_roomsFragment_to_paymentFragment)
            }
        }
    }
}