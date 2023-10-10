package com.example.tourtime.firebase.authentication.ui

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.tourtime.firebase.authentication.model.AuthUser
import com.example.tourtime.firebase.authentication.repository.AuthRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepo
) : ViewModel() {

    fun createUser(authUser: AuthUser) = repo.createUser(authUser)

    fun loginUser(authUser: AuthUser) = repo.loginUser(authUser)

    fun createUserWithPhone(
        mobile:String,
        activity:Activity
    ) = repo.createUserWithPhone(mobile,activity)

    fun signInWithCredential(
        code:String
    ) = repo.signWithCredential(code)

}