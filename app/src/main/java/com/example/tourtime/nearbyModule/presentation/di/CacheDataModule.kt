package com.example.tourtime.nearbyModule.presentation.di

import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByCache
import com.example.tourtime.nearbyModule.data.dataSource.NearByCache
import com.example.tourtime.nearbyModule.dataSourceImpl.cacheSourceImpl
import com.example.tourtime.nearbyModule.detailsdataSourceImpl.detailscacheSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class CacheDataModule {
    @Provides
    @Singleton
    fun provideCacheImpl(): NearByCache
    {
        return cacheSourceImpl()
    }
}


@Module
@InstallIn(SingletonComponent::class)

class DetailsCacheDataModule {
    @Provides
    @Singleton
    fun detailsprovideCacheImpl():DetailsNearByCache
    {
        return detailscacheSourceImpl()
    }
}
