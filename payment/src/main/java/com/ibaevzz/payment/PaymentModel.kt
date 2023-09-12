package com.ibaevzz.payment

import com.google.gson.annotations.SerializedName

data class PaymentModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("hotel_name")
    val hotelName: String,

    @SerializedName("hotel_adress")
    val hotelAddress: String,

    @SerializedName("horating")
    val rating: Int,

    @SerializedName("rating_name")
    val ratingName: String,

    @SerializedName("departure")
    val departure: String,

    @SerializedName("arrival_country")
    val arrivalCountry: String,

    @SerializedName("tour_date_start")
    val dateStart: String,

    @SerializedName("tour_date_stop")
    val dateStop: String,

    @SerializedName("number_of_nights")
    val number_of_nights: Int,

    @SerializedName("room")
    val room: String,

    @SerializedName("nutrition")
    val nutrition: String,

    @SerializedName("tour_price")
    val price: Int,

    @SerializedName("fuel_charge")
    val fuelCharge: Int,

    @SerializedName("service_charge")
    val serviceCharge: Int
)