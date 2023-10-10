package com.example.tourtime.nearbyModule.presentation.di

import com.example.tourtime.nearbyModule.data.RepositoryImpl
import com.example.tourtime.nearbyModule.data.dataSource.NearByCache
import com.example.tourtime.nearbyModule.data.dataSource.NearByLocalData
import com.example.tourtime.nearbyModule.data.dataSource.*
import com.example.tourtime.nearbyModule.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DataResoucreModule
{
    @Provides
    @Singleton
    fun provideDataResourceToRepo(
        nearByLocalData: NearByLocalData,
        nearByCache: NearByCache,
        remotoDataSource: RemotoDataSource
    ): Repository
    {
        return RepositoryImpl(nearByLocalData,nearByCache,remotoDataSource)
    }


}