package com.ibaevzz.hotel.network

import com.ibaevzz.hotel.HotelModel

class HotelRepository(private val hotelAPIClient: HotelAPIClient){
    suspend fun getHotels(): HotelModel {
        return hotelAPIClient.getHotelInfo()
    }
}