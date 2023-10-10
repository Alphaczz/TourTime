package com.example.tourtime.nearbyModule.data

import android.util.Log
import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByCache
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsNearByLocalData
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import com.example.tourtime.nearbyModule.domain.DetailsRepository

class DetailsRepositoryImpl (
    private val detailsNearByLocalData: DetailsNearByLocalData,
    private val detailsNearByCache: DetailsNearByCache,
    private val detailsRemotoDataSource: DetailsRemotoDataSource
): DetailsRepository {
    override suspend fun getResult(placeid: String): Result{
        return getforNearByCache(placeid)

    }
val apikey=BuildConfig.API_KEY
    override suspend fun updateResult(result: Result): Result{
        detailsNearByLocalData.clearALl()
        detailsNearByLocalData.saveNearbyResultToDb(result)
        detailsNearByLocalData.saveNearbyResultToDb(result)
        detailsNearByCache.saveResultCache(result)
        return result
    }
    private suspend fun getforNearByCache(placeid: String): Result {

        var result: Result = Result("","","","",emptyList(),"",0.0,"",
            emptyList(),"",0,0,"","",false)
        try {
            result = detailsNearByCache.getResultFromCache()
            Log.i("MyApp","Came from cache ")

        } catch (exeception: Exception) {
            Log.i("ErrorIngetnearByCache","Data Not uploaded")
        }
        if (result!=null) {

            result= getnearByLocalData(placeid)
            detailsNearByCache.saveResultCache(result)
        }
        return  result

    }


    suspend fun getnearByLocalData(placeid:String):Result
    {
        var result: Result = Result("","","","",emptyList(),"",0.0,"",
            emptyList(),"",0,0,"","",false)
        try {
            result=detailsNearByLocalData.getresultFromDb(placeid)
            Log.i("MyApp","Came from local db")

        }
        catch (exeception:java.lang.Exception)
        {
            Log.i("ErrorIngetnearByLocalDB","Data Not uploaded for local db")
        }
        if(result!=null) {
            return result
        }
        else{
            result=getResultFromApi(placeid)
            detailsNearByLocalData.saveNearbyResultToDb(result)

        }
        return result
    }
    suspend fun getResultFromApi(placeid:String): Result
    {
       var result: Result = Result("","","","",emptyList(),"",0.0,"",
           emptyList(),"",0,0,"","",false)
        try {
            val response= detailsRemotoDataSource.ProvidesDetails(placeid,apikey)
            val body=response.await().body()
            if(body!=null)
            {
                result=body.result
                Log.i("MyApp","Came from api after checking local db")
            }

        }catch (exeception:java.lang.Exception)
        {

        }
        return result


    }



}