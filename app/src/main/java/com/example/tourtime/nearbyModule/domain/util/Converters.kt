package com.example.tourtime.nearbyModule.domain.util

import androidx.room.TypeConverter
import com.example.tourtime.nearbyModule.data.datamodel.Photo
import com.example.tourtime.nearbyModule.data.datamodelDetails.Review

import com.google.android.libraries.places.api.model.OpeningHours
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPhotosList(photos: List<Photo?>?): String {
        if (photos == null) {
            return "[]"
        }
        return gson.toJson(photos)
    }

    @TypeConverter
    fun toPhotosList(json: String): List<Photo?> {
        val listType = object : TypeToken<List<Photo?>>() {}.type
        return gson.fromJson(json, listType)
    }
    @TypeConverter
    fun fromJson(json: String?): List<Review?> {
        if (json == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Review?>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun toJson(list: List<Review?>): String {
        return gson.toJson(list)
    }
    @TypeConverter
    fun toDatabaseValue(openingHours: OpeningHours): String {
        return gson.toJson(openingHours)
    }

    @TypeConverter
    fun toEntityValue(data: String): OpeningHours {
        return gson.fromJson(data, OpeningHours::class.java)
    }
}