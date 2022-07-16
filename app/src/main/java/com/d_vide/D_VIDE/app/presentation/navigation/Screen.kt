package com.d_vide.D_VIDE.app.presentation.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object RecruitingsScreen: Screen("recruitings_screen")
    object PostRecruitingScreen: Screen("post_recruiting_screen")
    object ReviewsScreen: Screen("reviews_screen")
    object TaggedReviewsScreen: Screen("tagged_reviews_screen")
}