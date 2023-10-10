package com.example.tourtime.nearbyModule.data.dataSource

import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import kotlinx.coroutines.Deferred

import retrofit2.Response


interface RemotoDataSource {
    suspend fun getResult(keyword:String,type:String) : Deferred<Response<NearbyModel>>
}