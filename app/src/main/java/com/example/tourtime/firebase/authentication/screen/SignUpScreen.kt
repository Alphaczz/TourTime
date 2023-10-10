package com.example.tourtime.firebase.authentication.screen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.R
import com.example.tourtime.firebase.authentication.model.AuthUser
import com.example.tourtime.firebase.authentication.ui.AuthViewModel
import com.example.tourtime.screens.ui.theme.TourTimeTheme
import com.example.tourtime.util.AppLogo
import com.example.tourtime.util.ResultState
import com.example.tourtime.util.showMsg
import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController,
                 viewModel: AuthViewModel = hiltViewModel()
)
{
    val context = LocalContext.current
val scope= rememberCoroutineScope()
    var email by remember {
        mutableStateOf("")
    }
    var showPassword by remember { mutableStateOf(value = false) }
    var showPassword1 by remember { mutableStateOf(value = false) }

    var password by remember {
        mutableStateOf("")
    }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmpassword by remember {
        mutableStateOf("")
    }
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
            Text(textAlign = TextAlign.Center,fontWeight = FontWeight.Medium,text = "Password Should Have Upper & Lower Character , Numbers and Special Characters (Example:Apple@123)")
Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "SignUp",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
            )
            OutlinedTextField(value = email,
                onValueChange = { email = it
                    emailError = if (isValidEmail(it)) "" else "Invalid email"
                },
                label = { Text("Email") },
                singleLine = true,
                isError = emailError.isNotEmpty(),
                placeholder = { Text(text = "Type Email here") },
                shape = RoundedCornerShape(percent = 20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp) ,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = password,
                onValueChange = { newText ->
                    password = newText
                    passwordError = if (isValidPassword(newText)) "" else "Invalid password"

                },
                label = {
                    Text(text = "Password")
                },
                isError = passwordError.isNotEmpty(),
                singleLine = true,
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
            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    color = Color.Red,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = confirmpassword,
                onValueChange = { newText ->
                    confirmpassword = newText
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = { Text(text = "Type password here") },
                shape = RoundedCornerShape(percent = 20),
                visualTransformation = if (showPassword1) {

                    VisualTransformation.None

                } else {

                    PasswordVisualTransformation()

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (showPassword1) {
                        IconButton(onClick = { showPassword1 = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword1 = true }) {
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
                scope.launch(Dispatchers.Main) {
                    if (password != confirmpassword ) {
                        context.showMsg("Password Not Matched",Toast.LENGTH_LONG)
                        password = ""
                        confirmpassword = ""
                    }
                    else if(emailError.isNotEmpty())
                    {
                        context.showMsg("Wrong Email Format",Toast.LENGTH_LONG)
                        email=""
                    }
                    else if (passwordError.isNotEmpty()){
                        context.showMsg("Use Strong Password",Toast.LENGTH_LONG)
                        password=""
                    }else {
                        viewModel.createUser(
                            AuthUser(
                                email,
                                password
                            )
                        ).collect {


                            when (it) {
                                is ResultState.Success -> {
                                    context.showMsg(it.data)
                                    false
                                }

                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    false
                                }

                                ResultState.Loading -> {
                                    true
                                }
                            }
                        }
                    }
                }

                             },modifier= Modifier
                .fillMaxWidth()
                .padding(12.dp))
            {
                Text(text = "SignUp Now",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { /*TODO*/ },modifier= Modifier
                .fillMaxWidth()
                .padding(6.dp))
            {
                Text(text = "Login Via OTP",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
            Button(onClick = {
                             },modifier= Modifier
                .fillMaxWidth()
                .padding(6.dp))
            {
                Text(text = "Login via Email",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }

        }

    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    TourTimeTheme {
        val  navController= rememberNavController()
        val mContext = LocalContext.current

        SignUpScreen(navController = navController)
    }
}
private fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isValidPassword(password: String): Boolean {
    val minLength = 8 // Minimum password length
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }

    return password.length >= minLength &&
            hasUppercase &&
            hasLowercase &&
            hasDigit &&
            hasSpecialChar
}