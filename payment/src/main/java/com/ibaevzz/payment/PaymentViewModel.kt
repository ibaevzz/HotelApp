package com.ibaevzz.payment

import android.util.Log
import androidx.lifecycle.*
import com.ibaevzz.payment.network.PaymentRepository
import kotlinx.coroutines.launch

class PaymentViewModel(private val repository: PaymentRepository): ViewModel() {

    private val _paymentModel: MutableLiveData<PaymentModel> = MutableLiveData()
    val paymentModel: LiveData<PaymentModel> = _paymentModel

    init {
        updateHotelModel()
        Log.i("zzz", "viewModel")
    }

    fun updateHotelModel(){
        viewModelScope.launch {
            _paymentModel.value = repository.getPayment()
        }
    }

    class Factory(private val repository: PaymentRepository): ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T:ViewModel> create(modelClass: Class<T>): T {
            if(modelClass==PaymentViewModel::class.java){
                return PaymentViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}