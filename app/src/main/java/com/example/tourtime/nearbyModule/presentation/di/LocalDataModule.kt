package com.example.tourtime.nearbyModule.presentation.di


import com.example.tourtime.nearbyModule.bookmarlDataSoourceImpl.BookmarkLocalImpl
import com.example.tourtime.nearbyModule.data.DB.bookmark.BookmarkDao
import com.example.tourtime.nearbyModule.data.DB.bookmark.BookmarkDao_Impl
import com.example.tourtime.nearbyModule.data.DB.details.DetailsDao
import com.example.tourtime.nearbyModule.data.DB.nearby.ResultDao
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.data.dataSource.NearByLocalData
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByLocalData
import com.example.tourtime.nearbyModule.detailsdataSourceImpl.DetailsLocalDataImpl
import com.example.tourtime.nearbyModule.dataSourceImpl.NearByLocalDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class localDataModule() {
    @Provides
    @Singleton
    fun provideLocalDataImpl(dao: ResultDao): NearByLocalData
   {
        return NearByLocalDataImpl(dao)
    }

}
@Module
@InstallIn(SingletonComponent::class)
class detailslocalDataModule() {
    @Provides
    @Singleton
    fun provideLocalDataImpl(dao: DetailsDao): DetailsNearByLocalData
    {
        return DetailsLocalDataImpl(resultDao =dao )
    }

}

@Module
@InstallIn(SingletonComponent::class)
class bookmarkLocalModule() {
    @Provides
    @Singleton
    fun provideBookmarkLocalDataImpl(dao: BookmarkDao): bookmarkLocalDataSource {
        return BookmarkLocalImpl(dao)
    }

}