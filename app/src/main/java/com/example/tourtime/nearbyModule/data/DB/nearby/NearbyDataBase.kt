package com.example.tourtime.nearbyModule.data.DB.nearby

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tourtime.nearbyModule.data.datamodel.Result
import com.example.tourtime.nearbyModule.domain.util.Converters


@Database(entities = [Result::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NearbyDataBase : RoomDatabase() {
    abstract fun ResultDao(): ResultDao
}

