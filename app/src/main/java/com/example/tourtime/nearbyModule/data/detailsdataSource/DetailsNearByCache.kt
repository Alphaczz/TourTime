package com.example.tourtime.nearbyModule.data.detailsdataSource

import com.example.tourtime.nearbyModule.data.datamodelDetails.Result


interface DetailsNearByCache {
    suspend fun getResultFromCache():Result
    suspend fun saveResultCache(result: Result)
}
