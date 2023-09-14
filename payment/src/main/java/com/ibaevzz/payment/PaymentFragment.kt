package com.ibaevzz.payment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibaevzz.main.ViewModelFactory
import com.ibaevzz.payment.databinding.PaymentFragmentBinding
import com.ibaevzz.payment.di.PaymentComponentProvider
import javax.inject.Inject

class PaymentFragment: Fragment() {

    private lateinit var binding: PaymentFragmentBinding
    private val mask = "(***)***-**-**"
    private val editText by lazy{
        binding.phone.editText!!
    }
    private val phone = MutableList(10){-1}

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
            findNavController().navigate(com.ibaevzz.main.R.id.action_paymentFragment_to_successFragment)
            //TODO
        }
        binding.root.setOnRefreshListener {
            viewModel.updateHotelModel()
        }
        viewModel.paymentModel.observe(this){
            binding.progress.visibility = View.GONE
            binding.root.isRefreshing = false
            binding.screen.visibility = View.VISIBLE
        }
        editText.addTextChangedListener(PhoneTextWatcher())
        editText.onFocusChangeListener = object: OnFocusChangeListener{
            var isFirstFocus = true
            override fun onFocusChange(view: View, isFocus: Boolean) {
                if(isFirstFocus){
                    editText.setText(mask)
                    isFirstFocus = false
                }
            }
        }
    }



    private inner class PhoneTextWatcher: TextWatcher {
        var selection = 1
        var len = 0
        var num = ""

        override fun onTextChanged(str: CharSequence, start: Int, before: Int, count: Int) {
            if(len == str.length){
                return
            }

            if(str.length<=14)
            len = str.length

            var i = 0
            while(i<phone.size){
                phone[i]=-1
                i+=1
            }

            var xx = 0
            i = 0
            for(s in str){
                if(s.toString()=="("||s.toString()==")"||s.toString()=="-")
                    xx+=1
                if(s.isDigit()&&i<10){
                    phone[i] = s.digitToInt()
                    i+=1
                }
            }

            var isM = false

            if(xx<4){
                isM = true
            }

            selection = 0
            var m = ""
            var isS = false
            var isD = false
            var firstP = false
            var secondP = false
            var c = 0
            var isDigit = true

            for(i in 1..14){
                if(c==0&&!isS){
                    m+="("
                    isS=true
                    if(isDigit){
                        selection+=1
                    }
                }else if(c==3&&!isD){
                    m+=")"
                    isD = true
                    if(isDigit){
                        selection+=1
                    }
                }else if(c==6&&!firstP){
                    m+="-"
                    firstP = true
                    if(isDigit){
                        selection+=1
                    }
                }else if(c==8&&!secondP){
                    m+="-"
                    secondP = true
                    if(isDigit){
                        selection+=1
                    }
                }
                else if(c<10){
                    if(isM&&c<9) {
                        m += if (phone[c + 1] > -1) {
                            selection += 1
                            phone[c].toString()
                        } else {
                            isDigit = false
                            "*"
                        }
                    }else{
                        m += if (phone[c] > -1) {
                            selection += 1
                            phone[c].toString()
                        } else {
                            isDigit = false
                            "*"
                        }
                    }
                    c+=1
                }
            }
            num = m
            editText.setText(m)
            editText.setSelection(selection)
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }
}