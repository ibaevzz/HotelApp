package com.ibaevzz.hotel

import android.util.Log
import androidx.lifecycle.*
import com.ibaevzz.hotel.network.HotelRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelViewModel @Inject constructor(private val repository: HotelRepository): ViewModel() {

    private val _hotelModel: MutableLiveData<HotelModel> = MutableLiveData()
    val hotelModel: LiveData<HotelModel> = _hotelModel

    init {
        updateHotelModel()
    }

    fun updateHotelModel(){
        viewModelScope.launch {
            _hotelModel.value = repository.getHotels()
        }
    }
}