package com.ibaevzz.hotel.di

import android.content.Context
import com.ibaevzz.hotel.network.HotelRepository
import com.ibaevzz.payment.di.PaymentComponent
import com.ibaevzz.payment.network.PaymentRepository
import com.ibaevzz.rooms.di.RoomsComponent
import com.ibaevzz.rooms.network.RoomsRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    fun hotelRepository(): HotelRepository
    fun roomsRepository(): RoomsRepository
    fun bookingRepository(): PaymentRepository

    val hotelComponent: HotelComponent.Factory
    val roomsComponent: RoomsComponent.Factory
    val paymentComponent: PaymentComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}