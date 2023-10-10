package com.example.tourtime.firebase.authentication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.appcompat.app.AppCompatActivity
import com.example.tourtime.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            // User is already logged in, open the MainActivity
            startActivity(Intent(this, Profile::class.java))
        } else {
            // User is not logged in, open the LoginActivity
            startActivity(Intent(this, AuthActivity::class.java))
        }

        finish()
    }
}