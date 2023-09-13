package com.ibaevzz.hotel.di

import com.ibaevzz.hotel.network.HotelAPIClient
import com.ibaevzz.hotel.network.HotelRepository
import com.ibaevzz.payment.network.PaymentAPIClient
import com.ibaevzz.payment.network.PaymentRepository
import com.ibaevzz.rooms.network.RoomsAPIClient
import com.ibaevzz.rooms.network.RoomsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideHotelRepository(api: HotelAPIClient): HotelRepository =
        HotelRepository(api)

    @Provides
    @Singleton
    fun provideRoomsRepository(api: RoomsAPIClient): RoomsRepository =
        RoomsRepository(api)

    @Provides
    @Singleton
    fun provideBookingRepository(api: PaymentAPIClient): PaymentRepository =
        PaymentRepository(api)
}