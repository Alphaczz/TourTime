package com.example.tourtime.nearbyModule.data.datamodelDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tourtime.nearbyModule.data.datamodel.Photo

@Entity(tableName = "Details")
data class Result(
    val adr_address: String?,
    val business_status: String?,
    val formatted_phone_number: String?,
    val name: String?,
    val photos: List<Photo?>?,
    @PrimaryKey
    val place_id: String,
    val rating: Double?,
    val reference: String?,
    val reviews: List<Review?>?, val url: String?,
    val user_ratings_total: Int?,
    val utc_offset: Int?,
    val vicinity: String?,
    val website: String?,
    val wheelchair_accessible_entrance: Boolean?
)
