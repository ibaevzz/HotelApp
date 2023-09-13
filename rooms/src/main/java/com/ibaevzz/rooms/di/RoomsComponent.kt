package com.ibaevzz.rooms.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibaevzz.main.di.FragmentScope
import com.ibaevzz.main.di.ViewModelKey
import com.ibaevzz.main.ViewModelFactory
import com.ibaevzz.rooms.RoomsFragment
import com.ibaevzz.rooms.RoomsViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [RoomsModule::class])
@FragmentScope
interface RoomsComponent {
    fun inject(fragment: RoomsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): RoomsComponent
    }
}

@Module
interface RoomsModule {
    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    @Binds
    fun bindBookingViewModel(impl: RoomsViewModel): ViewModel
}