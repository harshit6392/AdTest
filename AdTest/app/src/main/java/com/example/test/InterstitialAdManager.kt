package com.example.test
import android.adservices.common.AdData
import android.app.Activity
import android.content.Context
import android.os.ext.SdkExtensions
import androidx.annotation.RequiresExtension
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdManager private constructor() {
    private var interstitialAd: InterstitialAd? = null
    var isAdLoaded: Boolean = false
        private set

    @RequiresExtension(extension = SdkExtensions.AD_SERVICES, version = 4)
    fun initialize(context: Context, adUnitId: String) {
        val adRequest: AdData = AdData.Builder().build()
        InterstitialAd.load(context, adUnitId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                interstitialAd = ad
                isAdLoaded = true
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                interstitialAd = null
                isAdLoaded = false
            }
        })
    }

    fun showInterstitialAd(activity: Activity) {
        if (interstitialAd != null) {
            interstitialAd!!.show(activity)
            interstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    isAdLoaded = false
                    initialize(activity, "<YOUR_AD_UNIT_ID>")
                }
            }
        }
    }

    companion object {
        private var instance: InterstitialAdManager? = null
        fun getInstance(context: Context?): InterstitialAdManager {
            if (instance == null) {
                instance = InterstitialAdManager()
            }
            return instance!!
        }
    }
}