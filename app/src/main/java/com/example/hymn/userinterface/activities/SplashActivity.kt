package com.example.hymn.userinterface.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.hymn.R
import com.example.hymn.firestore.FirestoreClass
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
                    // If the user is logged in once and did not logged out manually from the app.
                    // So, next time when the user is coming into the app user will be redirected to MainScreen.
                    // If user is not logged in or logout manually then user will  be redirected to the Login screen as usual.

                    // Get the current logged in user id
                    val currentUserID = FirestoreClass().getCurrentUserID()

                    if (currentUserID.isNotEmpty()) {
                        // Launch dashboard screen.
                        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                    } else {
                        // Launch the Login Activity
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    }
                    finish()
                },
                2500
                // Here we pass the delay time in milliSeconds after which the splash activity will disappear.
        )

    }


}