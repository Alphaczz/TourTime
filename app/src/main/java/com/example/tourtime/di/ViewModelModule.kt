package com.example.tourtime.di

import com.example.tourtime.firebase.authentication.repository.AuthRepo
import com.example.tourtime.firebase.authentication.ui.AuthViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class ViewModelModule {


    @Provides
    @Singleton
    fun provideAuthViewModel(
        repo: AuthRepo
    )
            : AuthViewModelFactory {
        return AuthViewModelFactory(
            repo
        )
    }


}