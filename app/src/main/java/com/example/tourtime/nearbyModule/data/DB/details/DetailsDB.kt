package com.example.tourtime.nearbyModule.data.DB.details

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tourtime.nearbyModule.data.DB.details.DetailsDao
import  com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.domain.util.Converters

@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DetailsDB :RoomDatabase(){
    abstract fun DetailsDao(): DetailsDao

}

