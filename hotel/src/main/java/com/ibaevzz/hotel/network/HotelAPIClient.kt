package com.ibaevzz.hotel.network

import com.ibaevzz.hotel.HotelModel
import retrofit2.http.GET

interface HotelAPIClient {
    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotelInfo(): HotelModel
}