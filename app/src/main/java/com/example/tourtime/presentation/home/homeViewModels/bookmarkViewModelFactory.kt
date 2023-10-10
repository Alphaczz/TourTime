package com.example.tourtime.presentation.home.homeViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.domain.DetailsRepository

class BookmarkViewModelFactory(
    private val localDataSource: bookmarkLocalDataSource,
    private val detailsRepository: DetailsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(localDataSource, detailsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
