package com.example.tourtime.firebase.authentication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourtime.firebase.authentication.repository.AuthRepo

class AuthViewModelFactory (private val repo: AuthRepo
):ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModelFactory(repo) as T
    }
}