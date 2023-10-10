package com.example.tourtime.nearbyModule.presentation.di
import com.example.tourtime.nearbyModule.data.DetailsRepositoryImpl
import com.example.tourtime.nearbyModule.domain.DetailsRepository
import com.example.tourtime.nearbyModule.domain.Repository
import com.example.tourtime.nearbyModule.domain.usecase.updateResultUseCase
import com.example.tourtime.nearbyModule.domain.util.getResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class UseCaseModule {
    @Provides
    fun getResult(repository: Repository): getResultUseCase
    {
        return getResultUseCase(repository)
    }
    @Provides
    fun updateeResultUseCase(repository: Repository): updateResultUseCase
    {
        return updateResultUseCase(repository)
    }
}
