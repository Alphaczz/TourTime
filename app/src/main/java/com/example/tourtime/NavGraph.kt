package com.example.tourtime



import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.tourtime.MainDestinations.PLACE_DETAIL_ID
import com.example.tourtime.MainDestinations.PLACE_DETAIL_ROUTE
import com.example.tourtime.firebase.authentication.activity.SplashActivity
import com.example.tourtime.firebase.authentication.screen.LoginScreen
import com.example.tourtime.firebase.authentication.screen.SignUpScreen
import com.example.tourtime.nearbyModule.data.datamodelDetails.Details
import com.example.tourtime.presentation.home.HomeTabs
import com.example.tourtime.presentation.home.addHomeGraph
import com.example.tourtime.presentation.placedetail.DetailsScreen
import com.example.tourtime.presentation.placedetail.PlaceDetailsScreen
import com.example.tourtime.screens.LoginOption



object MainDestinations {
    const val HOME_ROUTE = "home"
    const val PLACE_DETAIL_ROUTE = "place"
    const val PLACE_DETAIL_ID = "placeId"
    const val SPLASH_ROUTE="splash"
    const val LOGIN ="login"
    const val SIGNUP="signup"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SPLASH_ROUTE
) {
    val actions = remember { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeTabs.FEED.route
        ) {
            addHomeGraph(
                navController = navController,
                navToPlaceDetail = { actions.navigateToPlaceDetail("temp") },
                modifier = modifier
            )
        }

        composable(
            route = "detail/{placeid}",
            arguments = listOf(
                navArgument("placeid") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val placeid = backStackEntry.arguments?.getString("placeid")
            DetailsScreen(navController = navController, placeid =placeid )
        }
        composable(route=MainDestinations.SPLASH_ROUTE){
            LoginOption(navController = navController)
        }
        composable(route=MainDestinations.LOGIN){
            LoginScreen(navController = navController)
        }
        composable(route=MainDestinations.SIGNUP)
        {
            SignUpScreen(navController = navController )
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToPlaceDetail = { placeId: String ->
        navController.navigate(route = "$PLACE_DETAIL_ROUTE/$placeId")
    }
}