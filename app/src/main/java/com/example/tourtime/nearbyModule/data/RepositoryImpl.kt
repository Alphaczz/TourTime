package com.example.tourtime.nearbyModule.data

import android.util.Log
import com.example.tourtime.nearbyModule.data.dataSource.NearByCache
import com.example.tourtime.nearbyModule.data.dataSource.*
import com.example.tourtime.nearbyModule.domain.Repository
import com.example.tourtime.nearbyModule.data.datamodel.Result

class RepositoryImpl (
    private val nearByLocalData: NearByLocalData,
    private val nearByCache: NearByCache,
    private val  remotoDataSource: RemotoDataSource
): Repository {
    override suspend fun getResult(keyword: String,type: String): List<Result>{
        return getforNearByCache(keyword = keyword, type = type)

    }

    override suspend fun updateResult(list: List<Result>): List<Result> {
        nearByLocalData.clearALl()
        nearByLocalData.saveNearbyResultToDb(list)
        nearByCache.saveResultCache(list)
        return list
    }
    private suspend fun getforNearByCache(keyword:String,type:String): List<Result> {

        var resultList1: List<Result> = emptyList()

        try {
            resultList1 = nearByCache.getResultFromCache()
            Log.i("MyApp","Came from cache ")

        } catch (exeception: Exception) {
            Log.i("ErrorIngetnearByCache","Data Not uploaded")
        }
        if (resultList1.isEmpty()) {

            resultList1= getnearByLocalData(keyword, type)
            nearByCache.saveResultCache(resultList1)
        }
        return  resultList1

    }


    suspend fun getnearByLocalData(keyword:String,type:String):List<Result>
    {
        var resultList: List<Result> = emptyList()
        try {
            resultList=nearByLocalData.getresultFromDb()
            Log.i("MyApp","Came from local db")

        }
        catch (exeception:java.lang.Exception)
        {
            Log.i("ErrorIngetnearByLocalDB","Data Not uploaded for local db")
        }
        if(resultList.isNotEmpty()) {
            return resultList
        }
        else{
            resultList=getResultFromApi(keyword, type)
            nearByLocalData.saveNearbyResultToDb(resultList)

        }
        return resultList
    }
    suspend fun getResultFromApi(keyword:String,type:String): List<Result>
    {
        var resultList: List<Result> = emptyList()
        try {
            val response= remotoDataSource.getResult(keyword = keyword,type=type)
            val body=response.await().body()
            if(body!=null)
            {
                resultList=body.results
                Log.i("MyApp","Came from api after checking local db")
            }

        }catch (exeception:java.lang.Exception)
        {

        }
        return resultList


    }



}