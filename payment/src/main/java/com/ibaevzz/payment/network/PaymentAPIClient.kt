package com.ibaevzz.payment.network

import com.ibaevzz.payment.PaymentModel
import retrofit2.http.GET

interface PaymentAPIClient {
    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getPaymentInfo(): PaymentModel
}