package com.example.flickstream.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flickstream.presentation.details.DetailsScreen

import com.example.flickstream.presentation.home.HomeScreen


@Composable
fun WatchAppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = { contentId ->
                    navController.navigate(Screen.Details.createRoute(contentId))
                }
            )
        }

        composable(
            route = Screen.Details.route,  // This will use "details/{contentId}"
            arguments = listOf(
                navArgument("contentId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailsScreen(
                contentId = backStackEntry.arguments?.getString("contentId") ?: "",
                contentType = "movie",  // Default value since we're not passing it in route
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
