package com.ibaevzz.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
    private var hotelName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(com.ibaevzz.main.R.string.hotel)
            setHomeAsUpIndicator(com.ibaevzz.main.R.drawable.arrow_back)
        }

        binding = FragmentHotelBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.root.setOnRefreshListener {
            viewModel.updateHotelModel()
        }
        binding.selectHotel.setOnClickListener{
            val args = Bundle()
            args.putString("hotel_name", hotelName)
            findNavController().navigate(
                resId = com.ibaevzz.main.R.id.action_hotelFragment_to_roomsFragment,
                args = args
            )
        }
        viewModel.hotelModel.observe(this){
            hotelName = it.name
            setView(it)
        }
    }

    private fun setView(hotelModel: HotelModel){
        binding.apply {
            root.isRefreshing = false
            progress.visibility = View.GONE
            mainInf.visibility = View.VISIBLE
            hotelAddress.text = hotelModel.address
            hotelName.text = hotelModel.name
            price.text = hotelModel.minimalPrice.toString()
            priceType.text = hotelModel.priceType
            description.text = hotelModel.hotelDetails.description
            facilities.layoutManager = FlexboxLayoutManager(context)
            facilities.adapter = FacilitiesAdapter(hotelModel.hotelDetails.peculiarities)
            photoSlider.adapter = SliderAdapter(hotelModel.imageUrls)
            rating.text = hotelModel.rating.toString()+" "+hotelModel.ratingName
        }
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