package com.example.tourtime.nearbyModule.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NearByPlace")
data class Result(
    val business_status: String?,
    val name: String?,
    val photos: List<Photo?>?,
    @PrimaryKey
    val place_id: String,
    val rating: Double?,
    val user_ratings_total: Int?,
    val vicinity: String?
)