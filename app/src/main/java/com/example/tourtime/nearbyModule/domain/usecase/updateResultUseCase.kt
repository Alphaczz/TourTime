package com.example.tourtime.nearbyModule.domain.usecase

import com.example.tourtime.nearbyModule.domain.Repository

import com.example.tourtime.nearbyModule.data.datamodel.Result
class updateResultUseCase(private val repository: Repository)
{
   suspend fun execute(list: List<Result>):List<Result>?=repository.updateResult(list)
}