package com.example.tourtime.nearbyModule.presentation.di
import android.app.Application
import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.api.NearByApi
import com.example.tourtime.nearbyModule.data.api.getDetails

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module

@InstallIn(SingletonComponent::class)
class NetModule (){

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.
        create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BuildConfig.BASE_URL).build()
    }
    @Singleton
    @Provides
    fun providesNearByService(retrofit: Retrofit
    ): NearByApi
    {
        return  retrofit.create(NearByApi::class.java)
    }

    @Singleton
    @Provides
    fun providesDetails(retrofit: Retrofit
    ): getDetails
    {
        return  retrofit.create(getDetails::class.java)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

}



