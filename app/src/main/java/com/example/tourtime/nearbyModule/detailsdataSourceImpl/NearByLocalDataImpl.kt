package com.example.tourtime.nearbyModule.detailsdataSourceImpl
import com.example.tourtime.nearbyModule.data.DB.details.DetailsDao
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByLocalData
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsLocalDataImpl(private  val resultDao: DetailsDao) : DetailsNearByLocalData {
    override suspend fun getresultFromDb(placeId:String): Result {
        return resultDao.getDetailsByPlaceId(placeId = placeId)
    }

    override suspend fun saveNearbyResultToDb(result: Result) {
        CoroutineScope(Dispatchers.IO).launch {
            resultDao.saveResult(result)
        }
    }

    override suspend fun clearALl() {
        CoroutineScope(Dispatchers.IO).launch{
            resultDao.deleteAllResult()
        }
    }
}
