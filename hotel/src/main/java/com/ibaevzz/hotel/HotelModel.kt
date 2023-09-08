package com.ibaevzz.hotel

import com.google.gson.annotations.SerializedName

data class HotelModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("adress")
    val address: String,

    @SerializedName("minimal_price")
    val minimalPrice: Int,

    @SerializedName("price_for_it")
    val priceType: String,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("rating_name")
    val ratingName: String,

    @SerializedName("image_urls")
    val imageUrls: List<String>,

    @SerializedName("about_the_hotel")
    val hotelDetails: HotelDetails

)

data class HotelDetails(
    @SerializedName("description")
    val description: String,

    @SerializedName("peculiarities")
    val peculiarities: List<String>
)