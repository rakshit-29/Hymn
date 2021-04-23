package com.example.hymn.userinterface.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.hymn.R
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Adding the handler to after the a task after some delay.
        Handler().postDelayed(
                {
                    // Launch the Main Activity
                    startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                    finish() // Call this when your activity is done and should be closed.
                },
                2500
                // Here we pass the delay time in milliSeconds after which the splash activity will disappear.
        )

    }
}