package com.ibaevzz.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ibaevzz.main.Constants
import com.ibaevzz.payment.databinding.PaymentFragmentBinding
import com.ibaevzz.payment.network.PaymentAPIClient
import com.ibaevzz.payment.network.PaymentRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentFragment: Fragment() {

    private lateinit var binding: PaymentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = getString(com.ibaevzz.main.R.string.booking)
            setHomeAsUpIndicator(com.ibaevzz.main.R.drawable.arrow_back)
        }
        binding = PaymentFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pay.setOnClickListener{

        }
        binding.root.setOnRefreshListener {
            viewModel.updateHotelModel()
        }
        viewModel.paymentModel.observe(this){
            binding.progress.visibility = View.GONE
            binding.root.isRefreshing = false
            binding.screen.visibility = View.VISIBLE
        }
    }

    //TODO make inject
    private fun getRepo(): PaymentRepository {
        return PaymentRepository(
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(PaymentAPIClient::class.java)
        )
    }

    //TODO make inject
    private val viewModel: PaymentViewModel by lazy {
        ViewModelProvider(requireActivity(), PaymentViewModel.Factory(getRepo()))[PaymentViewModel::class.java]
    }
}