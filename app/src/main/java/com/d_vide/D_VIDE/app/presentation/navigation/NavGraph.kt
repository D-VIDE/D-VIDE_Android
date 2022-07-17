package com.d_vide.D_VIDE.app.presentation.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // splash
        composable(route = Screen.Splash.route) {}

        // nav bar routes
        composable(route = Screen.RecruitingsScreen.route) {}
        composable(route = Screen.ReviewsScreen.route) {}
        composable(route = Screen.ChattingsScreen.route) {}
        composable(route = Screen.MyPageScreen.route) {}

        // routed by event
        composable(route = Screen.PostRecruitingScreen.route) {
            PostRecruitingScreen(navController)
        }
        composable(route = Screen.TaggedReviewsScreen.route) {}

    }
}