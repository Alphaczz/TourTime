package com.example.tourtime.nearbyModule.data

import android.location.Location
import android.util.Log

import com.example.guideme.domain.util.Resource
import com.example.tourtime.nearbyModule.data.api.NearByApi
import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import com.example.tourtime.nearbyModule.domain.NearByRepo
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NearbyApiImplementation @Inject constructor(private  val nearByApi: NearByApi) : NearByRepo {
    override suspend fun ProvidesNearByRepo(
        api_key: String,
        keyword: String,
        location:Location,
        radius: Int,
        type: String
    ): Response<NearbyModel> {
        val loc:String="${location.latitude},${location.longitude}"
        val response = nearByApi.getNearbyPlaces( keyword, loc,radius, type,api_key)
        if (response.isActive) {
            val nearbyModel = response.await()
            nearbyModel!!.body()!!.results.get(1).name?.let { Log.w("Loading", it) }


            return Response.success(nearbyModel.body())
        } else {
            val errorBody = response.await().errorBody()!!.string()
            val errorCode = response.await().code()


            return Response.error(errorCode, ResponseBody.create(null, errorBody!!))


        }
    }
    }

