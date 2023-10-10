package com.example.tourtime.presentation.placedetail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.font.FontWeight.*

import androidx.compose.ui.text.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.KingBed
import androidx.compose.material.icons.rounded.LocalPhone
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Web
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tourtime.R
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.guideme.domain.util.Resource
import com.example.tourtime.BuildConfig
import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import com.example.tourtime.nearbyModule.data.datamodelDetails.Result
import com.example.tourtime.nearbyModule.data.datamodelDetails.ResultBookmark
import com.example.tourtime.nearbyModule.domain.util.parseAddress
import com.example.tourtime.nearbyModule.presentation.DetailScreenViewModel
import com.example.tourtime.presentation.components.IconText
import com.example.tourtime.presentation.components.Menu
import com.example.tourtime.presentation.components.ReviewCard
import com.example.tourtime.presentation.components.TextLocation
import com.example.tourtime.presentation.components.Title
import com.example.tourtime.ui.theme.GreenNice
import com.example.tourtime.ui.theme.OrangeNice
import com.example.tourtime.ui.theme.PurpleNice
import com.example.tourtime.ui.theme.TravelAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Composable
fun DetailsScreen(navController: NavHostController, placeid:String?) {
    val viewModel: DetailScreenViewModel = hiltViewModel()


    LaunchedEffect(true) {

        viewModel.fetchNearbyData(placeid!!)

    }
    when (val nearbyDetailPlaces =viewModel.nearbyDetail.value) {
        is Resource.Loading -> {

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(), contentAlignment = Alignment.Center
            ){
                androidx.compose.material3.CircularProgressIndicator()

            }        }
        is Resource.Success<Result> -> {

            val nearbyData = (nearbyDetailPlaces )
           PlaceDetailsScreen(
               navBack = { /*TODO*/ },
               navigateToBookmark = { /*TODO*/ },
               result = nearbyData.data
           )
            Log.w("Loadingerror",nearbyData.data.adr_address!!)

        }
        is Resource.Error-> {
            val error = (nearbyDetailPlaces ).exception
            // Show an error message
            androidx.compose.material3.Text(text = "Error: ${error.message}")
        }


    }


}
@Composable
fun PlaceCard(modifier: Modifier = Modifier,result: Result) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp,
        modifier = modifier
    ) {

        Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 18.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = result.name!!, style = MaterialTheme.typography.h5)
                TextLocation(location ="India")
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                var language = result.formatted_phone_number
                if(language.isNullOrBlank())
                {
                    language="NA"
                }
                IconText(
                    icon = Icons.Rounded.LocalPhone,
                    color = GreenNice,
                    text = language,
                    textColored = false
                )
                val rate = result?.rating?.toString() ?: "0.0"

                IconText(
                    icon = Icons.Rounded.Star,
                    color = OrangeNice,
                    text = rate.toString(),
                    textColored = false
                )

            }
        }
    }
}
@Composable
fun ReviewSection(result: Result)
{
    if (!result.reviews.isNullOrEmpty()) {
       Text(text = "Reviews :",
           fontWeight = Companion.Bold)
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .wrapContentHeight(align = Alignment.CenterVertically, false)){
            items(result.reviews) { review ->
                ReviewCard(review = review)
            }
        }

    }
}


@Composable
fun GreatPlaceSection(result: Result) {

    Column {

        val address=parseAddress(result.adr_address!!)
        Title(text = result.vicinity!!)
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = address,
                style = MaterialTheme.typography.body2,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
          Row(Modifier.fillMaxWidth()) {
              var web =result.website
              if (web.isNullOrBlank())
              {
                  web="NaN"
              }
              IconText(
                  icon = Icons.Rounded.Web,
                  color = PurpleNice,
                  text = "Website :",
                  textColored = false,
                  big = true
              )
              val annotatedLinkString = buildAnnotatedString {
                  val str = web
                  val startIndex = str.indexOf("link")
                  val endIndex = startIndex + 4
                  append(str)
                  addStyle(
                      style = SpanStyle(
                          color = Color(0xff64B5F6),
                          textDecoration = TextDecoration.Underline
                      ), start = startIndex, end = endIndex
                  )
              }
              ClickableText(text = annotatedLinkString, style = MaterialTheme.typography.body1,
                  onClick = { @Composable {

                  }
                  }
              )
          }


        }

    
}
@Composable
fun PictureSection(result: Result) {

    Column {
        Title(text = "Picture")
        Row {
            for (i in 0..3) {
                val photo = result.photos?.getOrNull(0)
                if (photo != null) {
                    val photoReference = photo.photo_reference
                    val apiKey = BuildConfig.API_KEY
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
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }

                if (i < 3) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        val photo = result.photos?.getOrNull(0)
        if (photo != null) {
            val photoReference = photo.photo_reference
            val apiKey = BuildConfig.API_KEY
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
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}

@Composable
fun PlaceDetailsContent(result: Result) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        GreatPlaceSection(result)
        ReviewSection(result)
       // PictureSection(details)
    }
}


@Composable
fun BookNow(result: Result,viewModel: DetailScreenViewModel= hiltViewModel()) {
    Surface {
        var notification by remember { mutableStateOf(false) }
        if(notification)
        {
            Toast.makeText(LocalContext.current,"Bookmark Added",Toast.LENGTH_LONG)
            notification=false
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {

            val resultBookmark = ResultBookmark(
                adr_address = result.adr_address,
                business_status = result.business_status,
                formatted_phone_number = result.formatted_phone_number,
                name = result.name,
                photos = result.photos,
                place_id = result.place_id,
                rating = result.rating,
                reference = result.reference,
                reviews = result.reviews,
                url = result.url,
                user_ratings_total = result.user_ratings_total,
                utc_offset = result.utc_offset,
                vicinity = result.vicinity,
                website = result.website,
                wheelchair_accessible_entrance = result.wheelchair_accessible_entrance
            )
            Column(
                modifier = Modifier.weight(0.4f)
            ) {
                Text(
                    text = "Add to",
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(text = "Bookmark", style = MaterialTheme.typography.overline)
            }
            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        viewModel.saveToBookmark(resultBookmark);
                        notification=true

                    }

                },
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                    top = 13.dp,
                    bottom = 10.dp
                ),
                modifier = Modifier.weight(0.6f)
            ) {
                Text(text = "Bookmark")
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BookNowPreview() {
    TravelAppTheme {
       // BookNow()
    }
}

@Composable
fun PlaceDetailsScreen(
    navBack: () -> Unit,
    navigateToBookmark: () -> Unit,
    modifier: Modifier = Modifier,
    result: Result
) {
    val scrollState = rememberScrollState()
    Scaffold(
        bottomBar = { BookNow(result) },
        modifier = modifier
    ) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            val (image, card, content, menu) = createRefs()
            val photo = result.photos?.getOrNull(0)
            if (photo != null) {
                val photoReference = photo.photo_reference
                val apiKey = BuildConfig.API_KEY
                val maxWidth = 400
                val photoUrl =
                    "https://maps.googleapis.com/maps/api/place/photo?maxwidth=$maxWidth&photo_reference=$photoReference&key=$apiKey"

//            Image(
//                painter = painterResource(id = R.drawable.empty),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .height(370.dp)
//                    .constrainAs(image) {
//                        top.linkTo(parent.top)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                    }
//            )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photoUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(370.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                val cornerSize = 32.dp
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .clip(RoundedCornerShape(cornerSize))
                        .constrainAs(content) {
                            top.linkTo(image.bottom, margin = -cornerSize)
                        }
                ) {
                    PlaceDetailsContent(result = result)
                }

                PlaceCard(
                    modifier = Modifier.constrainAs(card) {
                        centerAround(content.top)
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)
                        width = Dimension.fillToConstraints
                    }, result
                )

                Menu(
                    modifier = Modifier.constrainAs(menu) {
                        top.linkTo(parent.top, margin = 24.dp)
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        width = Dimension.fillToConstraints
                    },
                    pressBack = navBack,
                    pressBookmark = navigateToBookmark
                )
            }
        }
    }


    @Composable
    fun PlaceDetailsScreenPreview() {
        TravelAppTheme {
//        PlaceDetailsScreen(
//            navBack = {},
//            navigateToBookmark = {},
//
//        )
        }
    }
}
