package com.example.tourtime.nearbyModule.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.tourtime.nearbyModule.data.DB.details.DetailsDB
import com.example.tourtime.nearbyModule.data.DB.details.DetailsDao
import com.example.tourtime.nearbyModule.data.DB.nearby.NearbyDataBase
import com.example.tourtime.nearbyModule.data.DB.nearby.ResultDao
import com.example.tourtime.nearbyModule.data.DB.bookmark.BookmarkDB
import com.example.tourtime.nearbyModule.data.DB.bookmark.BookmarkDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideNearbyDatabase(@ApplicationContext context: Context): NearbyDataBase {
        return (
            Room.databaseBuilder(
                context,
                NearbyDataBase::class.java, "NearbyPlace1"
            )
            .build()
        )

    }
    @Singleton
    @Provides
    fun provideDetailsDatabase(@ApplicationContext context: Context): DetailsDB {
        return (
                Room.databaseBuilder(
                    context,
                    DetailsDB::class.java, "DetailsDB"
                )
                    .build()
                )

    }
    @Singleton
    @Provides
    fun provideBookmarkDatabase(@ApplicationContext context: Context): BookmarkDB {
        return (
                Room.databaseBuilder(
                    context,
                    BookmarkDB::class.java, "BookmarkDB"
                )
                    .build()
                )

    }
    @Singleton
    @Provides
    fun provideNearbyDao(nearbyDataBase: NearbyDataBase): ResultDao {
        return  nearbyDataBase.ResultDao()
    }
    @Singleton
    @Provides
    fun provideDetailsDao(detailsDB: DetailsDB): DetailsDao {
        return  detailsDB.DetailsDao()
    }
    @Singleton
    @Provides
    fun provideBookmarkDao(bookmarkDB: BookmarkDB): BookmarkDao{
        return  bookmarkDB.BookmarkDao()
    }
}