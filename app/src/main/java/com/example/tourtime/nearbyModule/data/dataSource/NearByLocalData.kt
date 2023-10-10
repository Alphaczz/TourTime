package com.example.tourtime.nearbyModule.data.dataSource
import com.example.tourtime.nearbyModule.data.datamodel.Result

interface NearByLocalData {
    suspend fun getresultFromDb():List<Result>

    suspend fun saveNearbyResultToDb(resultList:List<Result>)

    suspend fun  clearALl()
}