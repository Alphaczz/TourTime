package com.example.tourtime.nearbyModule.presentation.di


import com.example.tourtime.nearbyModule.data.NearbyApiImplementation
import com.example.tourtime.nearbyModule.data.api.NearByApi
import com.example.tourtime.nearbyModule.domain.NearByRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NearByRepoModule {

    @Provides
    @Singleton
    fun provideNearByRepo(nearByApi: NearByApi): NearByRepo {
        return NearbyApiImplementation(nearByApi)
    }
}