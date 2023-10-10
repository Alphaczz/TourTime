package com.example.tourtime.firebase.authentication.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.firebase.authentication.screen.LoginScreen
import com.example.tourtime.firebase.authentication.screen.PhoneAuthScreen
import com.example.tourtime.screens.LoginOption
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material.Surface() {
                Scaffold {it.calculateBottomPadding().value
                    //    AuthScreen()
                    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    val navController = rememberNavController()
                     LoginOption(navController)
                }
            }
        }
    }
}