package com.d_vide.D_VIDE.app.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object SocialLoginScreen: Screen("social_login_screen")
    object HomeScreen: Screen("home_screen")
    object RecruitingsScreen: Screen("recruitings_screen")
    object PostRecruitingScreen: Screen("post_recruiting_screen")
    object ReviewsScreen: Screen("reviews_screen")
    object TaggedReviewsScreen: Screen("tagged_reviews_screen")
    object MyPageScreen: Screen("my_page_screen")
    object ChattingsScreen: Screen("chattings_screen")
    object MyFollowScreen: Screen("my_follow_screen")
    object OtherFollowScreen: Screen("other_follow_screen")
    object UserFeedScreen: Screen("user_feed_screen")
    object ReviewDetailScreen: Screen("review_detail_screen")
    object ChattingDetailScreen: Screen("chatting_detail_screen")
    object RecruitingDetailScreen: Screen("recruiting_detail_screen")
    object MyOrdersScreen: Screen("my_orders_screen")
    object PostReviewScreen: Screen("post_review_screen")
    object MyReviewsScreen: Screen("my_review_screen")
}

object DetailDestinationKey{
    const val RECRUITING = "recruitingId"
    const val REVIEW = "reviewId"
    const val CHATTING = "chattingId"
    const val TAGGEDREVIEW = "taggedReviewId"
}

object NavGraph{
    const val MAIN = "main_nav_graph"
    const val MYPAGE = "mypage_nav_graph"
    const val MYREVIEW = "myreview_nav_graph"
}

val shouldNotShowBottomScreen = listOf(
    Screen.PostReviewScreen.route,
    Screen.PostRecruitingScreen.route,
    Screen.SplashScreen.route,
    Screen.LoginScreen.route,
    Screen.ChattingDetailScreen.route+"/{${DetailDestinationKey.CHATTING}}"
)