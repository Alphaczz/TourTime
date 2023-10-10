package com.example.tourtime.nearbyModule.dataSourceImpl
import com.example.tourtime.nearbyModule.data.DB.nearby.ResultDao
import com.example.tourtime.nearbyModule.data.dataSource.NearByLocalData
import com.example.tourtime.nearbyModule.data.datamodel.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NearByLocalDataImpl(private  val resultDao: ResultDao) : NearByLocalData {
    override suspend fun getresultFromDb(): List<Result> {
        return resultDao.getAllResult()
    }

    override suspend fun saveNearbyResultToDb(result: List<Result>) {
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
