package com.example.tourtime.nearbyModule.data

import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import com.example.tourtime.nearbyModule.domain.BookmarkRepo

class BookmarkRepositoryImpl(): BookmarkRepo {
    override suspend fun getResult(): List<ResultBookmark> {
        TODO("Not yet implemented")
    }

    override suspend fun updateResult(result: Result): ResultBookmark {
        TODO("Not yet implemented")
    }
}