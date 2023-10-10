package com.example.tourtime.nearbyModule.data.DB.bookmark

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(result: ResultBookmark)

    @Query("Delete from bookmark")
    suspend fun deleteAllResult()

    @Query("SELECT * FROM bookmark")
    suspend fun getDetails(): List<ResultBookmark>

}
