package com.example.tourtime.nearbyModule.presentation.di

import android.content.Context
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.domain.DetailPlaces
import com.example.tourtime.nearbyModule.domain.DetailsRepository
import com.example.tourtime.nearbyModule.domain.NearByRepo
import com.example.tourtime.nearbyModule.domain.Repository
import com.example.tourtime.nearbyModule.domain.location.LocationTracker
import com.example.tourtime.nearbyModule.domain.usecase.updateResultUseCase
import com.example.tourtime.nearbyModule.domain.util.getResultUseCase
import com.example.tourtime.nearbyModule.presentation.DetailViewModelFactory
import com.example.tourtime.nearbyModule.presentation.nearbyViewModelFactory
import com.example.tourtime.presentation.home.homeViewModels.BookmarkViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class ViewModelModule {


    @Provides
    @Singleton
    fun provideViewModelFactory(
        getResultUseCase: getResultUseCase,
        updateResultUseCase: updateResultUseCase,
        repo: Repository,
        nearByRepo: NearByRepo,
        @ApplicationContext context: Context,
        locationTracker: LocationTracker
    )
            : nearbyViewModelFactory {
        return nearbyViewModelFactory(
            getResultUseCase,
            updateResultUseCase,
            repo,
            nearByRepo,
            context,
            locationTracker
        )
    }

    @Provides
    @Singleton
    fun provideDetailViewModelFactory(detailsRepository: DetailsRepository): DetailViewModelFactory {

   return DetailViewModelFactory(detailsRepository)
    }
    @Provides
    @Singleton
    fun provideBookmarkViewModelFactory(
        localDataSource: bookmarkLocalDataSource,
        detailsRepository: DetailsRepository // You should provide this dependency
    ): BookmarkViewModelFactory {
        return BookmarkViewModelFactory(localDataSource, detailsRepository)
    }
}