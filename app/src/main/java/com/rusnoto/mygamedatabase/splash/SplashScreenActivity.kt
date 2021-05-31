package com.rusnoto.mygamedatabase.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rusnoto.mygamedatabase.databinding.ActivitySplashScreenBinding
import com.rusnoto.mygamedatabase.home.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false)
        }else{
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        Handler(mainLooper).postDelayed({
            val moveToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(moveToMainActivity)
            finish()
        }, 3000)
    }
}