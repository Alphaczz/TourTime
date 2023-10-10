package com.example.tourtime.nearbyModule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import com.example.tourtime.nearbyModule.domain.DetailPlaces
import com.example.tourtime.nearbyModule.domain.DetailsRepository

class DetailViewModelFactory(
    private val detailsRepository: DetailsRepository

    ) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModelFactory(detailsRepository) as T
    }
}