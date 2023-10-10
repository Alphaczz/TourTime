package com.example.tourtime

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tourtime.presentation.home.HomeTabs
import com.example.tourtime.presentation.home.TravelAppBottomBar
import com.example.tourtime.screens.ui.theme.TourTimeTheme

@Composable
fun TourTimeApp(navController: NavHostController) {
    TourTimeTheme  {
        val tabs = remember { HomeTabs.values() }
        Scaffold(
            bottomBar = { TravelAppBottomBar(tabs = tabs, navController = navController) }
        ) { innerPadding ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
