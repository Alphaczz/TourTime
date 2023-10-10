package com.example.tourtime.nearbyModule.data.datamodelDetails

data class Review(
    var author_name: String,
    val language: String,
    val original_language: String,
    val profile_photo_url: String,
    val rating: Int,
    val relative_time_description: String,
    val text: String,
    val time: Int,

    )