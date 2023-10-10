package com.example.tourtime.presentation.home.homeViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guideme.domain.util.Resource
import com.example.tourtime.nearbyModule.data.bookmarkDataSOurce.bookmarkLocalDataSource
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import com.example.tourtime.nearbyModule.domain.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val localDataSource: bookmarkLocalDataSource,
    private val detailsRepository: DetailsRepository
) : ViewModel()
{
    private var _localData = mutableStateOf<Resource<List<ResultBookmark>>>(Resource.Loading)
    val localData: State<Resource<List<ResultBookmark>>> = _localData
    suspend fun getBookmarkData()
    {
        viewModelScope.launch {
            _localData.value=Resource.Loading
            try {
                val response= localDataSource.getresultFromDb()
                _localData.value=Resource.Success(response)
                Log.d("MyBookmark", "API call successful ")
            }
            catch (e: Exception)
            {
                _localData.value = Resource.Error(e)
                Log.e("MyBookmark", "API call error: ${e.message}")

            }

        }
    }
}