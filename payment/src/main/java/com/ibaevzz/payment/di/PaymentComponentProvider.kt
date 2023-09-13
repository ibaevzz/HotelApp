package com.ibaevzz.payment.di

interface PaymentComponentProvider {
    fun providePayment(): PaymentComponent
}