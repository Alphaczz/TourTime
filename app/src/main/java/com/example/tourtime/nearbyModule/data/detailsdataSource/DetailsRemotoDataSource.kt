package com.example.tourtime.nearbyModule.data.detailsdataSource

import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface DetailsRemotoDataSource {
    suspend fun  ProvidesDetails(
        placeId:String,
        api_key: String,
    ) : Deferred<Response<Details>>
}