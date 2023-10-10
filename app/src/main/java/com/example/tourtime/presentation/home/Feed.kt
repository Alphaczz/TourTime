package com.example.tourtime.presentation.home

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import com.example.tourtime.nearbyModule.data.datamodel.Result
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.guideme.domain.util.Resource
import com.example.tourtime.BuildConfig
import com.example.tourtime.R
import com.example.tourtime.nearbyModule.presentation.viewmodelNearby

import com.example.tourtime.presentation.components.PlaceCard
import com.example.tourtime.presentation.components.TextLocation
import com.example.tourtime.presentation.components.Title
import com.example.tourtime.ui.theme.*


@Composable
fun Feed(
    navToPlaceDetail: () -> Unit,
    navToProfile: () -> Unit,
    navToSearch: () -> Unit,
    navController: NavController,
    viewModel: viewmodelNearby = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    LaunchedEffect(true) {
        viewModel.fetchNearbyData()
    }


    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TravelAppTopBar(navToProfile)
        CategorySection()
        SearchSection(navToSearch)
        when (val nearbyDataState =viewModel.nearbyData.value) {
            is Resource.Loading -> {
                       Box(modifier = Modifier.align(Alignment.CenterHorizontally,
                           ), contentAlignment = Alignment.Center
                       ){
                           androidx.compose.material3.CircularProgressIndicator()

                       }
            }
            is Resource.Success<List<Result>> -> {

                Log.w("Loadingerror", nearbyDataState.data.size.toString())
                PopularPlace(results = nearbyDataState.data, navController)
            }
            is Resource.Error-> {
                val error = (nearbyDataState as Resource.Error).exception
                // Show an error message
                androidx.compose.material3.Text(text = "Error: ${error.message}")
            }

            else -> {}
        }

        RecommendedSection()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(
    color: Color,
    icon: ImageVector,
    text: String
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(width = 1.dp, color = color.copy(alpha = 0.1f)),
        color = MaterialTheme.colors.background,
        onClick = {}
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = 12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(color = color)
            ) {
                Icon(icon, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun CategorySection() {
    Column {
        Title(text = "Category")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            CategoryItem(color = BlueNice, icon = Icons.Rounded.GridView, text = "All")
            CategoryItem(color = OrangeNice, icon = Icons.Rounded.Landscape, text = "Hill")
            CategoryItem(color = PurpleNice, icon = Icons.Rounded.KingBed, text = "Hotel")
            CategoryItem(color = GreenNice, icon = Icons.Rounded.BeachAccess, text = "Beach")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchSection(navToSearch: () -> Unit) {
    val source = remember { MutableInteractionSource() }
    val borderColor = if (MaterialTheme.colors.isLight) {
        MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    } else {
        MaterialTheme.colors.surface
    }

    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = "Search",
            onValueChange = {},
            shape = MaterialTheme.shapes.medium,
            trailingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = borderColor),
            singleLine = true,
            readOnly = true,
            interactionSource = source,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            onClick = {},
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.primary.copy(alpha = 0.1f),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                Icon(
                    Icons.Rounded.FilterList,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }

    var clicked by remember { mutableStateOf(false) }
    if (source.collectIsPressedAsState().value && !clicked) {
        // Prevent double click
        @Suppress("UNUSED_VALUE")
        clicked = true

        navToSearch()
    }
}


@Composable
fun RecommendedSection() {
    Column {
        Title(text = "Recommended")
        Image(
            painter = painterResource(id = R.drawable.empty),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(MaterialTheme.shapes.medium)
        )

    }
}

@Composable
fun TravelAppTopBar(navToAccount: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Rounded.Menu, contentDescription = null)
        }

        TextLocation(location = "Mumbai", big = true)

        IconButton(onClick = navToAccount) {
            Image(
                painter = painterResource(R.drawable.empty),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
            )
        }

    }
}

@Composable
fun PopularPlace(results: List<Result>,navController: NavController) {

    LazyRow {
        items(results) { item: Result ->

            val photo = item.photos?.getOrNull(0)
            if (photo != null) {
                val photoReference = photo.photo_reference
                val apiKey = BuildConfig.API_KEY
                val maxWidth = 400
                val photoUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=$maxWidth&photo_reference=$photoReference&key=$apiKey"

                Log.w("ImageUrl1", photoReference)
Spacer(Modifier.width(5.dp))
                PlaceCard(
                    modifier = Modifier,
                    name = item.name ?: "Unknown",
                    location = item.business_status?: "Unknown",
                    photoUrl = photoUrl,
                    navController = navController,
                    onClick = {
                       navController.navigate("detail/${item.place_id}")
                    }
                        )



            }else
            {
                Toast.makeText(LocalContext.current,"No Details Available",Toast.LENGTH_LONG)
            }


        }
    }
}


@Preview
@Composable
fun SearchSectionPreview() {
    TravelAppTheme {
        SearchSection(navToSearch = {})
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    TravelAppTheme {
        CategoryItem(color = BlueNice, icon = Icons.Rounded.GridView, text = "All")
    }
}

@Preview
@Composable
fun PlaceCardPreview() {
    TravelAppTheme {
        PlaceCard(
            modifier = Modifier.width(200.dp),
            name = "Mumbai",
            location = "India",
            photoUrl = "",
            navController = rememberNavController(),
            onClick = {}
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FeedPreview() {
    TravelAppTheme {
        Surface(color = MaterialTheme.colors.background) {

//            Feed(
//                navToSearch = {},
//                navToProfile = {},
//                navToPlaceDetail = {},
//            )
        }
    }
}
