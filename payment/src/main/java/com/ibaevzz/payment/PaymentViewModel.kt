package com.ibaevzz.payment

import androidx.lifecycle.*
import com.ibaevzz.payment.network.PaymentRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentViewModel @Inject constructor(private val repository: PaymentRepository): ViewModel() {

    private val _paymentModel: MutableLiveData<PaymentModel> = MutableLiveData()
    val paymentModel: LiveData<PaymentModel> = _paymentModel

    init {
        updateHotelModel()
    }

    fun updateHotelModel(){
        viewModelScope.launch {
            _paymentModel.value = repository.getPayment()
        }
    }
}