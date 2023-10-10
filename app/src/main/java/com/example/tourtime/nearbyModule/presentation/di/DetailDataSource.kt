package com.example.tourtime.nearbyModule.presentation.di
import com.example.tourtime.nearbyModule.data.DetailsRepositoryImpl
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByCache
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByLocalData
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource

import com.example.tourtime.nearbyModule.domain.DetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DetailDataSource {
    @Provides
    @Singleton
    fun DetailprovideDataResourceToRepo(
        detailnearByLocalData: DetailsNearByLocalData,
        detailnearByCache: DetailsNearByCache,
        detailremotoDataSource: DetailsRemotoDataSource
    ): DetailsRepository
    {
       return  DetailsRepositoryImpl(detailnearByLocalData,detailnearByCache,detailremotoDataSource)
    }
}


