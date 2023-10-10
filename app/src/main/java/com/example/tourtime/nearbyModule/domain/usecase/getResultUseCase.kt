package com.example.tourtime.nearbyModule.domain.util


import com.example.tourtime.nearbyModule.data.datamodel.Result
import com.example.tourtime.nearbyModule.domain.Repository

class getResultUseCase (private val repository: Repository){
    suspend fun execute():List<Result>?=repository.getResult("Mountain","Tour")
}
