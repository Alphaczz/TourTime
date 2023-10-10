package com.example.tourtime.nearbyModule.detailsdataSourceImpl

import android.util.Log
import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import com.example.tourtime.nearbyModule.data.api.getDetails
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class DetailApiImpl @Inject constructor(private  val getDetails: getDetails) : DetailsRemotoDataSource {
    override suspend fun  ProvidesDetails(
        placeId:String,
        api_key: String,
    ):Deferred<Response<Details>> {

        val response = getDetails.getPlaceDetails(placeId,api_key)
        if (response.isSuccessful) {
            val detailsModel = response.body()
            Log.w("Loading",detailsModel!!.result!!.name!!)

            return CompletableDeferred(response)

        } else {
            val errorBody = response.errorBody()?.string()
            val errorCode = response.code()


            throw Exception("Request failed with code ${response.code()}")


        }
    }
}