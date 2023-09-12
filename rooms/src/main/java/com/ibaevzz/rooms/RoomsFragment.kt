package com.ibaevzz.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibaevzz.main.Constants
import com.ibaevzz.rooms.adapters.RoomsAdapter
import com.ibaevzz.rooms.databinding.FragmentRoomsBinding
import com.ibaevzz.rooms.network.RoomsAPIClient
import com.ibaevzz.rooms.network.RoomsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoomsFragment: Fragment() {

    private lateinit var binding: FragmentRoomsBinding

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

    //TODO make inject
    private fun getRepo(): RoomsRepository {
        return RoomsRepository(
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RoomsAPIClient::class.java)
        )
    }

    //TODO make inject
    private val viewModel: RoomsViewModel by lazy {
        ViewModelProvider(requireActivity(), RoomsViewModel.Factory(getRepo()))[RoomsViewModel::class.java]
    }

}