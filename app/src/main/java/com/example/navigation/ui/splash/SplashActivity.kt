package com.example.navigation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.R
import com.example.navigation.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() { // geri gelince countdowntimer çalışması için
        object :
            CountDownTimer(5000, 1000) { //animasyon devam ederken süre sayıp main activity geçecek
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }.start()
        super.onResume()
    }

}