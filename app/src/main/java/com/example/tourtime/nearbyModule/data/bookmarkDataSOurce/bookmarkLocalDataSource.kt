package com.example.tourtime.nearbyModule.data.bookmarkDataSOurce

import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark

interface bookmarkLocalDataSource {

        suspend fun getresultFromDb(): List<ResultBookmark>

        suspend fun saveToBookmark(result: ResultBookmark)

        suspend fun  clearALl()

}