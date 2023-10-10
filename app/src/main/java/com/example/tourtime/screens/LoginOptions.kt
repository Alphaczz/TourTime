package com.example.tourtime.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tourtime.MainDestinations
import com.example.tourtime.R
import com.example.tourtime.screens.ui.theme.TourTimeTheme
import com.example.tourtime.util.AppLogo
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.layout.Spacer as Spacer



@Composable
fun LoginOption(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login Options") },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        }
    ) {it.calculateBottomPadding().value
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp),

        ) {
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp),
                
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                AppLogo(R.drawable.tour,2f)
            }



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                
            ) {
                Button(
                    onClick = {
                        navController.navigate(MainDestinations.LOGIN)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("Login")
                }

                Button(
                    onClick = {
                        navController.navigate(MainDestinations.SIGNUP)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("Sign Up")
                }
                Button(
                    onClick = {
                        // Handle login with email/password
                        // You can perform authentication here
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("Login Via OTP")
                }


                // Add buttons for other login options like Google, Facebook, etc.
                // Each button should have an onClick action to handle the respective login method.
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    TourTimeTheme {
          LoginOption(navController = NavController(LocalContext.current))
    }
}