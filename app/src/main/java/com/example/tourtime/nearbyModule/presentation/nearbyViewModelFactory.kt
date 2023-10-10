package com.example.tourtime.nearbyModule.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourtime.nearbyModule.domain.NearByRepo
import com.example.tourtime.nearbyModule.domain.Repository
import com.example.tourtime.nearbyModule.domain.location.LocationTracker
import com.example.tourtime.nearbyModule.domain.usecase.updateResultUseCase
import com.example.tourtime.nearbyModule.domain.util.getResultUseCase


class nearbyViewModelFactory(private val getResultUsecase: getResultUseCase,
                             private val updateResultUseCase: updateResultUseCase,
                             private val repo: Repository,
                             private val nearByRepo: NearByRepo,
                             private val context: Context,
                             private val locationTracker: LocationTracker,
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return nearbyViewModelFactory(getResultUsecase,updateResultUseCase,repo,nearByRepo,context,locationTracker)as T
    }
}