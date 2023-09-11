package com.ibaevzz.rooms

import com.google.gson.annotations.SerializedName

data class RoomsModel(

    @SerializedName("rooms")
    val rooms: List<RoomModel>

)

data class RoomModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("price_per")
    val priceType: String,

    @SerializedName("peculiarities")
    val peculiarities: List<String>,

    @SerializedName("image_urls")
    val imageUrls: List<String>

)