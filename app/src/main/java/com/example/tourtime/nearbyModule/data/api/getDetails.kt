package com.example.tourtime.nearbyModule.data.api


import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface getDetails {
    @GET("maps/api/place/details/json")
    suspend fun getPlaceDetails(
        @Query("place_id") placeId: String,
        @Query("key") apiKey: String
    ): Response<Details>
}
