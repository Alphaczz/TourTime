package com.example.tourtime.nearbyModule.presentation.di

import com.example.tourtime.nearbyModule.data.api.NearByApi
import com.example.tourtime.nearbyModule.data.api.getDetails
import com.example.tourtime.nearbyModule.data.dataSource.RemotoDataSource
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import com.example.tourtime.nearbyModule.dataSourceImpl.remoteDataSourceImpl
import com.example.tourtime.nearbyModule.detailsdataSourceImpl.DetailApiImpl
import com.example.tourtime.nearbyModule.domain.location.LocationTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDateModule() {
    @Provides
    @Singleton
    fun provideRemoteDataImpl(nearByApi: NearByApi,locationTracker: LocationTracker): RemotoDataSource
    {
        return remoteDataSourceImpl(nearbyapi = nearByApi, locationTracker = locationTracker)
    }

}
