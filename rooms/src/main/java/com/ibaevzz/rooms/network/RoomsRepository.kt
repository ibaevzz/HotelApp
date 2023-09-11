package com.ibaevzz.rooms.network

import com.ibaevzz.rooms.RoomsModel

class RoomsRepository(private val roomsAPIClient: RoomsAPIClient){
    suspend fun getRooms(): RoomsModel {
        return roomsAPIClient.getRoomsInfo()
    }
}