package com.example.tourtime.nearbyModule.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guideme.domain.util.Resource
import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import com.example.tourtime.nearbyModule.data.detailsdataSource.DetailsRemotoDataSource
import com.example.tourtime.nearbyModule.domain.DetailPlaces
import com.example.tourtime.nearbyModule.domain.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository,
    private val bookmarkLocalDataSource: bookmarkLocalDataSource
) :ViewModel()
{

    private val _nearbyDetailData = mutableStateOf<Resource<Result>>(Resource.Loading)
    val nearbyDetail: State<Resource<Result>> = _nearbyDetailData
    suspend  fun fetchNearbyData(placeId:String) {
        viewModelScope.launch {

            _nearbyDetailData.value = Resource.Loading
            try {
                val response = detailsRepository.getResult(placeId)
                 val body=response
                _nearbyDetailData.value = Resource.Success(body)
                Log.d("MyApp", "API call successful ")
            } catch (e: Exception) {
                _nearbyDetailData.value = Resource.Error(e)
                Log.e("MyApp", "API call error: ${e.message}")
            }
        }
    }
    suspend fun saveToBookmark(result: ResultBookmark)
    {

            try {
            bookmarkLocalDataSource.saveToBookmark(result = result)
                Log.e("MyApp", "Saved to Bookmark")

            }
            catch (e: Exception) {
                Log.e("MyApp", "API call error: ${e.message}")
            }

    }
}