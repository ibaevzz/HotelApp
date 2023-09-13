package com.ibaevzz.hotel.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibaevzz.hotel.HotelFragment
import com.ibaevzz.hotel.HotelViewModel
import com.ibaevzz.main.di.FragmentScope
import com.ibaevzz.main.di.ViewModelKey
import com.ibaevzz.main.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [HotelModule::class])
@FragmentScope
interface HotelComponent {
    fun inject(fragment: HotelFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HotelComponent
    }
}

@Module
interface HotelModule {
    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    @Binds
    fun bindHotelViewModel(impl: HotelViewModel): ViewModel
}