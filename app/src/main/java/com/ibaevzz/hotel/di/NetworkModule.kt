package com.ibaevzz.hotel.di

import com.ibaevzz.hotel.network.HotelAPIClient
import com.ibaevzz.main.Constants.BASE_URL
import com.ibaevzz.payment.network.PaymentAPIClient
import com.ibaevzz.rooms.network.RoomsAPIClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHotelAPIClient(client: OkHttpClient): HotelAPIClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HotelAPIClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomsAPIClient(client: OkHttpClient): RoomsAPIClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RoomsAPIClient::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentAPIClient(client: OkHttpClient): PaymentAPIClient {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PaymentAPIClient::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .build()
}