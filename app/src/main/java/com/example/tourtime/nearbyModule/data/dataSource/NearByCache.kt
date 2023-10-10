package com.example.tourtime.nearbyModule.data.dataSource

import com.example.tourtime.nearbyModule.data.datamodel.Result


interface NearByCache {
    suspend fun getResultFromCache():List<Result>
    suspend fun saveResultCache(result: List<Result>)
}
