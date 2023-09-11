package com.ibaevzz.rooms

import android.util.Log
import androidx.lifecycle.*
import com.ibaevzz.rooms.network.RoomsRepository
import kotlinx.coroutines.launch

class RoomsViewModel(private val repository: RoomsRepository): ViewModel() {
    private val _roomsModel: MutableLiveData<RoomsModel> = MutableLiveData()
    val roomsModel: LiveData<RoomsModel> = _roomsModel

    init {
        updateRoomsModel()
        Log.i("zzz", "viewModel")
    }

    fun updateRoomsModel(){
        viewModelScope.launch {
            _roomsModel.value = repository.getRooms()
        }
    }

    class Factory(private val repository: RoomsRepository): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T:ViewModel> create(modelClass: Class<T>): T {
            if(modelClass==RoomsViewModel::class.java){
                return RoomsViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}