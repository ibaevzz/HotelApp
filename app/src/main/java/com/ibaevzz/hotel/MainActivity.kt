package com.ibaevzz.hotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibaevzz.hotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.container, HotelFragment()).commit()
    }
}