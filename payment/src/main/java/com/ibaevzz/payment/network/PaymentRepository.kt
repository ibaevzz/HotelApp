package com.ibaevzz.payment.network

import com.ibaevzz.payment.PaymentModel

class PaymentRepository(private val paymentAPIClient: PaymentAPIClient){
    suspend fun getPayment(): PaymentModel {
        return paymentAPIClient.getPaymentInfo()
    }
}