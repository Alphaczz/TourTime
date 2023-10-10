package com.example.tourtime.nearbyModule.bookmarlDataSoourceImpl

import com.example.tourtime.nearbyModule.data.DB.bookmark.BookmarkDao
import com.example.tourtime.nearbyModule.data.DB.nearby.ResultDao
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.data.dataSource.NearByLocalData
import com.example.tourtime.nearbyModule.data.datamodel.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkLocalImpl(private  val resultDao:BookmarkDao) :bookmarkLocalDataSource{
    override suspend fun getresultFromDb(): List<ResultBookmark>{
        return resultDao.getDetails()
    }

    override suspend fun saveToBookmark(result: ResultBookmark) {
        CoroutineScope(Dispatchers.IO).launch {
            resultDao.saveResult(result)
        }
    }

    override suspend fun clearALl() {
        CoroutineScope(Dispatchers.IO).launch {
            resultDao.deleteAllResult()
        }
    }
    }