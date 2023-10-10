package com.example.tourtime.nearbyModule.domain

import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark

interface BookmarkRepo {
    suspend fun getResult():List<ResultBookmark>
    suspend fun  updateResult(result: Result):ResultBookmark
}