package com.ibaevzz.rooms

import androidx.lifecycle.*
import com.ibaevzz.rooms.network.RoomsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsViewModel @Inject constructor(private val repository: RoomsRepository): ViewModel() {
    private val _roomsModel: MutableLiveData<RoomsModel> = MutableLiveData()
    val roomsModel: LiveData<RoomsModel> = _roomsModel

    init {
        updateRoomsModel()
    }

    fun updateRoomsModel(){
        viewModelScope.launch {
            _roomsModel.value = repository.getRooms()
        }
    }
}