package com.ibaevzz.hotel

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexboxLayoutManager
import com.ibaevzz.hotel.databinding.FragmentHotelBinding
import com.ibaevzz.hotel.di.HotelComponentProvider
import com.ibaevzz.main.adapters.FacilitiesAdapter
import com.ibaevzz.main.adapters.SliderAdapter
import com.ibaevzz.main.ViewModelFactory
import javax.inject.Inject

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private var hotelName: String = ""

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: HotelViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as HotelComponentProvider).provideHotel().inject(this)
    }

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
            dots.addSlider(photoSlider)
        }
    }
}