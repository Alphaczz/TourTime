package com.example.tourtime.nearbyModule.domain

import android.location.Location

import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import retrofit2.Response

interface DetailPlaces {
    suspend fun ProvidesDetails (Places:String,key:String) : Response<Details>
}