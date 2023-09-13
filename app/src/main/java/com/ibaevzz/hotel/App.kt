package com.ibaevzz.hotel

import android.app.Application
import com.ibaevzz.hotel.di.AppComponentProvider
import com.ibaevzz.hotel.di.HotelComponent
import com.ibaevzz.hotel.di.HotelComponentProvider
import com.ibaevzz.payment.di.PaymentComponent
import com.ibaevzz.payment.di.PaymentComponentProvider
import com.ibaevzz.rooms.di.RoomsComponent
import com.ibaevzz.rooms.di.RoomsComponentProvider

class App: Application(), RoomsComponentProvider, HotelComponentProvider, PaymentComponentProvider {
    override fun onCreate() {
        super.onCreate()
        AppComponentProvider.build(this)
    }

    override fun provideHotel(): HotelComponent {
        return AppComponentProvider.appComponent().hotelComponent.create()
    }

    override fun providePayment(): PaymentComponent {
        return AppComponentProvider.appComponent().paymentComponent.create()
    }

    override fun provideRooms(): RoomsComponent {
        return AppComponentProvider.appComponent().roomsComponent.create()
    }
}