package com.example.tourtime.presentation.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Business
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.MobileFriendly
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key.Companion.Bookmark
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.guideme.domain.util.Resource
import com.example.tourtime.BuildConfig
import com.example.tourtime.R
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark

import com.example.tourtime.presentation.components.Menu
import com.example.tourtime.presentation.components.TextLocation
import com.example.tourtime.presentation.home.homeViewModels.BookmarkViewModel
import com.example.tourtime.presentation.home.homeViewModels.BookmarkViewModelFactory
import com.example.tourtime.presentation.placedetail.PlaceDetailsScreen
import com.example.tourtime.ui.theme.OrangeNice
import com.example.tourtime.ui.theme.TravelAppTheme


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SaveItem(
    bookmark:ResultBookmark,navController: NavController
) {
    Card(onClick = {navController.navigate("detail/${bookmark.place_id}")}) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .focusTarget()
                .shadow(
                    0.5.dp,
                    shape = RectangleShape,
                    clip = false,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.LightGray
                )
        ) {

            val photo = bookmark.photos?.getOrNull(0)
            val photoReference = photo?.photo_reference ?: ""
            val apiKey = BuildConfig.API_KEY ?: ""
            val maxWidth = 400
            val photoUrl =
                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=$maxWidth&photo_reference=$photoReference&key=$apiKey"

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(width = 90.dp, height = 110.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End) {
                    Text(
                        text = bookmark?.name ?: "Name not available",
                        style = MaterialTheme.typography.h4,
                        fontSize = 18.sp,
                        modifier = Modifier.width(150.dp),
                        maxLines = 2
                    )
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = OrangeNice,
                        modifier = Modifier.padding(35.dp,0.dp,2.dp,0.dp).clickable {

                        }
                    )
                }


                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = bookmark?.vicinity ?: "Vicinity not available",
                        style = MaterialTheme.typography.body1,
                        fontSize = 10.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                    Icon(
                        Icons.Rounded.Star,
                        contentDescription = null,
                        tint = OrangeNice,
                        modifier = Modifier.padding(end = 2.dp)
                    )

                    Text(
                        text = bookmark?.rating?.toString() ?: "Rating not available",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        Icons.Rounded.Business,
                        contentDescription = null,
                        tint = OrangeNice,
                        modifier = Modifier.padding(end = 2.dp)
                    )

                    Text(
                        text = bookmark?.business_status?: "NA",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 2.dp)
                    )



                }
            }                    }
                }
            }




    @Composable
    fun Bookmark(
        navBack: () -> Unit,
        modifier: Modifier = Modifier,
        navController: NavController,
        resultBookmark: List<ResultBookmark>
    ) {

        Column(modifier = modifier.padding(all = 16.dp)) {
            Menu(
                pressBack = navBack,
                title = "Save"
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
                items(resultBookmark) { resultBookmark ->
                    SaveItem(
                        resultBookmark,navController
                    )
                }
            }
        }

    }

    @Composable
    fun ShowBookmark(navController:NavController) {
        val viewModel: BookmarkViewModel = hiltViewModel()

        LaunchedEffect(true) {

            viewModel.getBookmarkData()

        }
        when (val data = viewModel.localData.value) {
            is Resource.Loading -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator()

                }
            }

            is Resource.Success<List<ResultBookmark>> -> {

                val Bookmarks = data?.data ?: emptyList()
                Log.i("Bookmark at ShowBookmark", "${Bookmarks.size}")
                Bookmark(navBack = { /*TODO*/ }, resultBookmark = Bookmarks, navController = navController)
            }

            is Resource.Error -> {
                val error = (data).exception
                // Show an error message
                androidx.compose.material3.Text(text = "Error: ${error.message}")
            }


        }

    }



//@Preview
//@Composable
//fun SaveItemPreview() {
//    TravelAppTheme {
////        SaveItem(
////            image = R.drawable.empty,
////            title = "The Great Wall of China",
////            stars = "4.5",
////            location = "China"
////        )
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun SaveScreenPreview() {
//    TravelAppTheme {
//        // Bookmark(navBack = {})
//    }
//}