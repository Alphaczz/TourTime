package com.example.tourtime.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tourtime.nearbyModule.data.datamodelDetails.Review
import com.example.tourtime.ui.theme.Shapes

@Composable
fun ReviewCard(review: Review?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(200.dp),
        shape = Shapes.medium,

    ) {
        val scroll =  rememberScrollState(0)

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Display user profile photo
            val profileImageUrl = review?.profile_photo_url ?: ""
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                val authorName = review?.author_name ?: "Unknown Author"
                Text(text = "Author: $authorName", fontWeight = FontWeight.Bold)

                val rating = review?.rating ?: 0.0
                Text(text = "Rating: $rating")

                val relativeTime = review?.relative_time_description ?: "Unknown Time"
                Text(text = "Relative Time: $relativeTime", color = Color.Gray)

                val language = review?.language ?: "Unknown Language"
                Text(text = "Language: $language")

                val originalLanguage = review?.original_language ?: "Unknown Original Language"
                Text(text = "Original Language: $originalLanguage")

                // Display more fields as needed
                // ...

                Text(text = "Review: ${review?.text}",
                    modifier = Modifier
                        .heightIn(100.dp,150.dp)
                        .verticalScroll(scroll))
            }
        }
    }
}

@Preview
@Composable
fun ReviewCardPreview() {
    val review = Review(
        author_name = "John Doe",
        language = "English",
        original_language = "English",
        profile_photo_url = "https://example.com/profile_photo.jpg",
        rating = 4,
        relative_time_description = "2 days ago",
        text = "This place was amazing! Highly recommended.",
        time = 1629758400 // Example timestamp
    )

    ReviewCard(review = review)
}
