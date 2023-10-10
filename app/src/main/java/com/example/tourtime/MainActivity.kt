package com.example.tourtime

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.Manifest

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.firebase.authentication.screen.LoginScreen
import com.example.tourtime.firebase.authentication.screen.SignUpScreen
import com.example.tourtime.screens.ui.theme.TourTimeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth;


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        var currentUser = auth.getCurrentUser()

    }
    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        auth=Firebase.auth

        super.onCreate(savedInstanceState)
        setContent {
            RequestPermission(permission = Manifest.permission.ACCESS_FINE_LOCATION)
            val navController:NavHostController= rememberNavController()
                         TourTimeApp(navController)
                }

        }
    }








@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TourTimeTheme {
    }
}