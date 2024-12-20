package com.example.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adManager = InterstitialAdManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Ad Manager
        adManager.initialize(this, "<YOUR_AD_UNIT_ID>")

        // Button Click Listener
        binding.showAdButton.setOnClickListener {
            if (adManager.isAdLoaded()) {
                adManager.showInterstitialAd(this)
            } else {
                Toast.makeText(this, "Ad is not loaded yet", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
