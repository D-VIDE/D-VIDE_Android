package com.d_vide.D_VIDE.app.presentation.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object RecruitingsScreen: Screen("recruitings_screen")
    object PostRecruitingScreen: Screen("post_recruiting_screen")
    object ReviewsScreen: Screen("reviews_screen")
    object TaggedReviewsScreen: Screen("tagged_reviews_screen")
    object MyPageScreen: Screen("my_page_screen")
    object ChattingsScreen: Screen("chattings_screen")
    object FollowingsScreen: Screen("followings_screen")
    object UserFeedScreen: Screen("user_feed_screen")
    object ReviewDetailScreen: Screen("review_detail_screen")
    object ChattingDetailScreen: Screen("chatting_detail_screen")
}

object DetailDestinationKey{
    const val REVIEW = "reviewId"
    const val CHATTING = "chattingId"
}