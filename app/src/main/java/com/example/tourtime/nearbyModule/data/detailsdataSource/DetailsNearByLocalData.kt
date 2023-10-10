package com.example.tourtime.nearbyModule.data.detailsdataSource
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result

interface DetailsNearByLocalData {
    suspend fun getresultFromDb(placeId:String):Result

    suspend fun saveNearbyResultToDb(resultList:Result)

    suspend fun  clearALl()
}