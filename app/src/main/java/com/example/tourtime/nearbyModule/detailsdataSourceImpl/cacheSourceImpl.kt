package com.example.tourtime.nearbyModule.detailsdataSourceImpl
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByCache
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
class detailscacheSourceImpl: DetailsNearByCache {
    private var NearByResultList:Result=Result("","","","",emptyList(),"",0.0,"",
        emptyList(),"",0,0,"","",false)

    override suspend fun getResultFromCache(): Result{
        return NearByResultList
    }

    override suspend fun saveResultCache(result: Result) {
        NearByResultList=Result("","","","",emptyList(),"",0.0,"",
            emptyList(),"",0,0,"","",false)
        NearByResultList=result
    }
}

