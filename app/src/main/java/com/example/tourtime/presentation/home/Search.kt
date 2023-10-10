package com.example.tourtime.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.R
import com.example.tourtime.presentation.components.Menu
import com.example.tourtime.presentation.components.PlaceCard
import com.example.tourtime.presentation.components.Title
import com.example.tourtime.ui.theme.TravelAppTheme

@Composable
fun SearchField() {
    val borderColor = if (MaterialTheme.colors.isLight) {
        MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
    } else {
        MaterialTheme.colors.surface
    }

    OutlinedTextField(
        value = "Search",
        onValueChange = {},
        shape = MaterialTheme.shapes.medium,
        trailingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = borderColor),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NearBySection() {
    val images = listOf(
        R.drawable.empty,
        R.drawable.empty,
        R.drawable.empty
    )
    Column {
        Title(text = "Near by")
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(count = 3) { key ->
                Image(
                    painter = painterResource(id = images[key]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
            }
        }
    }
}

@Composable
fun RecentSearchSection(navToPlaceDetail: () -> Unit,navController: NavController) {
    Column {
        Title(text = "Recent Search")
        Row {
            PlaceCard(
                modifier = Modifier.weight(1f),
                name = "Mumbai",
                location = "India",
                photoUrl ="",
                navController = navController,
                onClick = navToPlaceDetail
            )
            Spacer(modifier = Modifier.width(16.dp))
            PlaceCard(
                modifier = Modifier.weight(1f),
                name = "Thane",
                location = "India",
                photoUrl = "",
                navController = navController,
                onClick = navToPlaceDetail
            )
        }
    }
}

@Composable
fun PopularSection() {
    Column {
        Title(text = "Recommended")
        Row {
            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }
    }
}

@Composable
fun Search(
    pressBack: () -> Unit,
    navToPlaceDetail: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
            .padding(all = 24.dp)
            .verticalScroll(state = scrollState)
    ) {
        Menu(pressBack = pressBack)
        SearchField()
        NearBySection()
        RecentSearchSection(navToPlaceDetail,navController)
        PopularSection()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    TravelAppTheme {
        Search(
            pressBack = {},
            navToPlaceDetail = {},
            navController = rememberNavController()
        )
    }
}
