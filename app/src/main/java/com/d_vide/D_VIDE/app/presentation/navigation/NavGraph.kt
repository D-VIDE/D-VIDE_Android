package com.d_vide.D_VIDE.app.presentation.navigation

import android.window.SplashScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingScreen
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingViewModel
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.TaggedReviewsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    upPress: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RecruitingsScreen.route,
    ) {
        // splash
        composable(route = Screen.ReviewsScreen.route) {}

        // nav bar routes
        composable(route = Screen.RecruitingsScreen.route) {
            RecruitingsScreen(navController)
        }
        composable(route = Screen.ReviewsScreen.route) {}
        composable(route = Screen.ChattingsScreen.route) {}
        composable(route = Screen.MyPageScreen.route) {}

        // routed by event
        composable(route = Screen.PostRecruitingScreen.route) {
            PostRecruitingScreen(navController, upPress)
        }
        composable(route = Screen.TaggedReviewsScreen.route) {
            TaggedReviewsScreen(navController)
        }

    }
}