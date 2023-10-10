package com.example.tourtime.nearbyModule.data.DB.nearby

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tourtime.nearbyModule.data.datamodel.Result

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(result: List<Result>)

    @Query("Delete from NearByPlace")
    suspend fun deleteAllResult()

    @Query("Select * from NearByPlace")
    suspend fun getAllResult():List<Result>
}