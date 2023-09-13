package com.ibaevzz.payment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibaevzz.main.di.FragmentScope
import com.ibaevzz.payment.PaymentFragment
import com.ibaevzz.payment.PaymentViewModel
import com.ibaevzz.payment.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [PaymentModule::class])
@FragmentScope
interface PaymentComponent {
    fun inject(fragment: PaymentFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PaymentComponent
    }
}

@Module
interface PaymentModule {
    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    fun bindPaymentViewModel(impl: PaymentViewModel): ViewModel
}