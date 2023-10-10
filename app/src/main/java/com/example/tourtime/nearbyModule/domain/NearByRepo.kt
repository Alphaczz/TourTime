package com.example.tourtime.nearbyModule.domain

import android.location.Location
import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import retrofit2.Response

interface NearByRepo {
    suspend fun ProvidesNearByRepo (  api_key:String,  keyword:String,
                                       location:Location, radius:Int,  type:String) : Response<NearbyModel>
}