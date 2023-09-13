package com.ibaevzz.payment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibaevzz.main.ViewModelFactory
import com.ibaevzz.payment.databinding.PaymentFragmentBinding
import com.ibaevzz.payment.di.PaymentComponentProvider
import javax.inject.Inject

class PaymentFragment: Fragment() {

    private lateinit var binding: PaymentFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PaymentViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as PaymentComponentProvider).providePayment().inject(this)
    }

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
}