package com.example.tourtime.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppLogo(resource:Int,scale:Float) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = null, // Provide a content description if needed
        modifier = Modifier
            .size(300.dp)
            .padding(8.dp)
            .scale(scale),

        )
}