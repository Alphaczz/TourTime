package com.example.tourtime.nearbyModule.domain
import com.example.tourtime.nearbyModule.data.datamodel.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getResult(keyword:String,type:String): List<Result>
    suspend fun  updateResult(list: List<Result>):List<Result>
}

