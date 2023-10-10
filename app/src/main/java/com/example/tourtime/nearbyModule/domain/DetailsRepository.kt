package com.example.tourtime.nearbyModule.domain

import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
interface DetailsRepository{
    suspend fun getResult(placeid:String): Result
    suspend fun  updateResult(result: Result):Result
}