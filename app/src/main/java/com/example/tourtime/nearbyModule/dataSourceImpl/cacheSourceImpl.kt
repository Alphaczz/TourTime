package com.example.tourtime.nearbyModule.dataSourceImpl
import com.example.tourtime.nearbyModule.data.dataSource.NearByCache
import com.example.tourtime.nearbyModule.data.datamodel.Result
class cacheSourceImpl: NearByCache {
    private var NearByResultList= ArrayList<Result>()

    override suspend fun getResultFromCache(): List<Result> {
        return NearByResultList
    }

    override suspend fun saveResultCache(result: List<Result>) {
        NearByResultList.clear()
        NearByResultList=ArrayList(result)
    }
}

