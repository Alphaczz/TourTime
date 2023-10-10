package com.example.tourtime.nearbyModule.data.DB.details

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import  com.example.tourtime.nearbyModule.data.datamodelDetails.Result



@Dao
interface DetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(result: Result)

    @Query("Delete from Details")
    suspend fun deleteAllResult()

    @Query("SELECT * FROM Details WHERE place_id = :placeId")
    suspend fun getDetailsByPlaceId(placeId: String): Result

}


