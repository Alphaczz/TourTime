package com.example.tourtime.nearbyModule.data.api

import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NearByApi {
    @GET("maps/api/place/nearbysearch/json")
    fun getNearbyPlaces(
        @Query("keyword") keyword: String,
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): Deferred<Response<NearbyModel>>
}



