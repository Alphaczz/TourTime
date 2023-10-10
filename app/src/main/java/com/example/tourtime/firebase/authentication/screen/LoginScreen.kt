package com.example.tourtime.firebase.authentication.screen

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import android.os.Bundle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.Placeholder
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.MainActivity
import com.example.tourtime.MainDestinations
import com.example.tourtime.R
import com.example.tourtime.firebase.authentication.model.AuthUser
import com.example.tourtime.firebase.authentication.ui.AuthViewModel
import com.example.tourtime.screens.LoginOption
import com.example.tourtime.screens.ui.theme.TourTimeTheme
import com.example.tourtime.util.AppLogo
import com.example.tourtime.util.ResultState
import com.example.tourtime.util.showMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController)
{


    val viewModel: AuthViewModel = hiltViewModel()

    var email by remember {
   mutableStateOf("")
   }
    val scope = rememberCoroutineScope()


    var showPassword by remember { mutableStateOf(value = false) }

    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .padding(20.dp)
                .height(150.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter


        ) {
             AppLogo(resource = R.drawable.tour,0.8f)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "Email Login",
                style = androidx.compose.material3.MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
            )
            OutlinedTextField(value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                placeholder = { Text(text = "Type Email here") },
                shape = RoundedCornerShape(percent = 20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = password,
                onValueChange = { newText ->
                    password = newText
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
                visualTransformation = if (showPassword) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {

                scope.launch(Dispatchers.Main){
                    viewModel.loginUser(
                        AuthUser(
                            email,
                            password
                        )
                    ).collect{
                        when(it){
                            is ResultState.Success -> {
                                context.showMsg(it.data)
                                false
                                navController.navigate(MainDestinations.HOME_ROUTE)

                            }
                            is ResultState.Failure->{
                                context.showMsg(it.msg.toString())
                                false
                            }
                            ResultState.Loading->{
                                true
                            }
                        }
                    }
                }


        },modifier=Modifier.
            fillMaxWidth().padding(12.dp))
            {
                  Text(text = "Login",
                      color = Color.White,
                      fontSize = 15.sp
                  )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { /*TODO*/ },modifier=Modifier.
            fillMaxWidth().padding(6.dp))
            {
                Text(text = "Login Via OTP",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
            Button(onClick = { /*TODO*/ },modifier=Modifier.
            fillMaxWidth().padding(6.dp))
            {
                Text(text = "Not Have An Account ?",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

        }

    }

}

