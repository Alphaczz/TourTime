package com.example.tourtime.nearbyModule.presentation.di


import com.example.tourtime.nearbyModule.data.api.getDetails
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import com.example.tourtime.nearbyModule.detailsdataSourceImpl.DetailApiImpl
import com.example.tourtime.nearbyModule.domain.DetailPlaces
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DetailModule {
    @Provides
    @Singleton
   fun providegetDetails(getDetails: getDetails): DetailsRemotoDataSource
    {
       return DetailApiImpl(getDetails = getDetails)
   }
}