package com.example.tourtime.nearbyModule.dataSourceImpl

import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.api.NearByApi
import com.example.tourtime.nearbyModule.data.dataSource.RemotoDataSource
import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import com.example.tourtime.nearbyModule.domain.location.LocationTracker
import kotlinx.coroutines.Deferred
import retrofit2.Response

class remoteDataSourceImpl(private val nearbyapi:NearByApi,
                           private val locationTracker: LocationTracker,
) : RemotoDataSource
{
    override suspend fun getResult(keyword: String, type: String): Deferred<Response<NearbyModel>> {
        val loc = locationTracker.getCurrentLocation()
        val location:String="${loc?.latitude},${loc?.longitude}"
        return nearbyapi.getNearbyPlaces(
              keyword = keyword,
              location = location,
              radius = 10000,
              type = type,
              BuildConfig.API_KEY,

            )
    }

}