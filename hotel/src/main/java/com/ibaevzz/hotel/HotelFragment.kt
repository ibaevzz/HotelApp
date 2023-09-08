package com.ibaevzz.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayoutManager
import com.ibaevzz.hotel.databinding.FragmentHotelBinding
import com.ibaevzz.hotel.network.HotelAPIClient
import com.ibaevzz.hotel.network.HotelRepository
import com.ibaevzz.main.Constants
import com.ibaevzz.main.FacilitiesAdapter
import com.ibaevzz.main.SliderAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater)
        binding.facilities.layoutManager = FlexboxLayoutManager(context)
        binding.root.setOnRefreshListener {
            viewModel.updateHotelModel()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.hotelModel.observe(this){
            setView(it)
        }
    }

    private fun setView(hotelModel: HotelModel){
        binding.root.isRefreshing = false
        binding.progress.visibility = View.GONE
        binding.mainInf.visibility = View.VISIBLE
        binding.hotelAddress.text = hotelModel.address
        binding.hotelName.text = hotelModel.name
        binding.price.text = hotelModel.minimalPrice.toString()
        binding.priceType.text = hotelModel.priceType
        binding.description.text = hotelModel.hotelDetails.description
        binding.facilities.adapter = FacilitiesAdapter(hotelModel.hotelDetails.peculiarities)
        binding.photoSlider.adapter = SliderAdapter(hotelModel.imageUrls)
        binding.rating.text = hotelModel.rating.toString()+" "+hotelModel.ratingName
    }

    //TODO make inject
    private fun getRepo(): HotelRepository{
        return HotelRepository(
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(HotelAPIClient::class.java)
        )
    }

    //TODO make inject
    private val viewModel: HotelViewModel by lazy {
        ViewModelProvider(requireActivity(), HotelViewModel.Factory(getRepo()))[HotelViewModel::class.java]
    }

}