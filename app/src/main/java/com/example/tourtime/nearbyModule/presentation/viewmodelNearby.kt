package com.example.tourtime.nearbyModule.presentation


import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope

import com.example.guideme.domain.util.Resource
import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.datamodel.NearbyModel
import com.example.tourtime.nearbyModule.data.datamodel.Result
import com.example.tourtime.nearbyModule.domain.NearByRepo
import com.example.tourtime.nearbyModule.domain.Repository
import com.example.tourtime.nearbyModule.domain.location.LocationTracker
import com.example.tourtime.nearbyModule.domain.usecase.updateResultUseCase
import com.example.tourtime.nearbyModule.domain.util.getResultUseCase
import com.google.android.libraries.places.api.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewmodelNearby @Inject constructor(
    private val getResultUsecase: getResultUseCase,
    private val updateResultUseCase: updateResultUseCase,
    private val repo: Repository,
    private val nearByRepo: NearByRepo,
    private val context: Context,
    private val locationTracker: LocationTracker,
) : ViewModel() {
    private val _nearbyData = mutableStateOf<Resource<List<Result>>>(Resource.Loading)
    //val nearbyData: State<Resource<NearbyModel>> = _nearbyData
    var nearbyData:State<Resource<List<Result>>> = _nearbyData

    // Specify the fields to return.
    suspend fun fetchNearbyData() {
        viewModelScope.launch {

            if (isConnectedToInternetAndLocation(context)) {
                //_nearbyData.value = Resource.Loading
                try {
//                    val response = nearByRepo.ProvidesNearByRepo(
//                        BuildConfig.API_KEY,
//                        keyword = "Tourist",
//                        location = loc,
//                        radius = 10000,
//                        type = "Nature"
//                    )
                    val response=repo.getResult("Mountain","Popular")
                   _nearbyData.value = Resource.Success(response)

                    Log.d("MyApp", "API call successful ${(_nearbyData.value as Resource.Success<List<Result>>).data.get(0).name}")
                } catch (e: Exception) {
                    _nearbyData.value = Resource.Error(e)
                    Log.e("MyApp", "API call error: ${e.message}")
                }
            } else {
                // Handle the case where loc is null (e.g., location services are disabled or not available).
                val response=repo.getResult("Mountain","Popular")
                _nearbyData.value = Resource.Success(response)
                Log.e("MyApp", "API call error:else part")

            }
        }




    }
    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    @SuppressLint("ServiceCast")
    private fun isConnectedToInternetAndLocation(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) &&
                    isLocationEnabled(context)
        } else {
            // For devices running versions below Android M (API 23), use a deprecated method
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected && isLocationEnabled(context)
        }
    }
}





