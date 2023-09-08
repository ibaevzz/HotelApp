package com.ibaevzz.hotel

import android.util.Log
import androidx.lifecycle.*
import com.ibaevzz.hotel.network.HotelRepository
import kotlinx.coroutines.launch

class HotelViewModel(private val repository: HotelRepository): ViewModel() {

    private val _hotelModel: MutableLiveData<HotelModel> = MutableLiveData()
    val hotelModel: LiveData<HotelModel> = _hotelModel

    init {
        updateHotelModel()
        Log.i("zzz", "viewModel")
    }

    fun updateHotelModel(){
        viewModelScope.launch {
            _hotelModel.value = repository.getHotels()
        }
    }

    class Factory(private val repository: HotelRepository): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T:ViewModel> create(modelClass: Class<T>): T {
            if(modelClass==HotelViewModel::class.java){
                return HotelViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}