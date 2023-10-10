package com.example.tourtime.firebase.authentication.repository

import android.app.Activity
import com.example.tourtime.firebase.authentication.model.AuthUser
import com.example.tourtime.util.ResultState
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    fun createUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>
    fun createUserWithGoogle(
        auth: AuthUser
    ) :Flow<ResultState<String>>
    fun loginUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>
//  fun buildSignInRequest():Flow<ResultState<BeginSignInRequest>>
    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ) : Flow<ResultState<String>>

    fun signWithCredential(
        otp:String
    ):  Flow<ResultState<String>>

}