package com.example.tourtime.nearbyModule.data.DB.bookmark

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import com.example.tourtime.nearbyModule.domain.util.Converters

@Database(entities = [ResultBookmark::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookmarkDB():RoomDatabase() {
    abstract fun BookmarkDao():BookmarkDao
}

