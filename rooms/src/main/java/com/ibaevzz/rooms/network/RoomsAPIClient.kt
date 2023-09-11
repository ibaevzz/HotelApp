package com.ibaevzz.rooms.network

import com.ibaevzz.rooms.RoomsModel
import retrofit2.http.GET

interface RoomsAPIClient {
    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRoomsInfo(): RoomsModel
}