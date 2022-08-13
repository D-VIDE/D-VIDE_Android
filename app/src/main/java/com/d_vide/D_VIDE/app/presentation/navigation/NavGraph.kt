package com.d_vide.D_VIDE.app.presentation.navigation


import androidx.navigation.*
import androidx.navigation.compose.composable
import com.d_vide.D_VIDE.app.presentation.ChattingDetail.ChattingDetail
import com.d_vide.D_VIDE.app.presentation.Chattings.Chattings
import com.d_vide.D_VIDE.app.presentation.MyPage.MyPageScreen
import com.d_vide.D_VIDE.app.presentation.Followings.FollowingsScreen
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.PostRecruitingScreen
import com.d_vide.D_VIDE.app.presentation.RecruitingDetail.RecruitingDetail
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.app.presentation.ReviewDetail.ReviewDetail
import com.d_vide.D_VIDE.app.presentation.Reviews.Reviews
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.TaggedReviewsScreen
import com.d_vide.D_VIDE.app.presentation.UserFeed.UserFeedScreen


fun NavGraphBuilder.divideGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Int, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
){
    navigation(
        route = Screen.Splash.route,
        startDestination = Screen.RecruitingsScreen.route
    ) {
        MainNavGraph(
            onReviewClick = onReviewClick,
            onChattingClick = onChattingClick,
            onTagClick = onTagClick,
            onRecruitingClick = onRecruitingClick,
            upPress = upPress,
            navController = navController
        )
    }

    composable(
        "${Screen.ReviewDetailScreen.route}/{${DetailDestinationKey.REVIEW}}",
        arguments = listOf(navArgument(DetailDestinationKey.REVIEW) { type = NavType.IntType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val reviewId = arguments.getInt(DetailDestinationKey.REVIEW)
        ReviewDetail(
            reviewId = reviewId,
            upPress,
            onTagClick = { id -> onTagClick(id, backStackEntry)})
    }

    composable(
        "${Screen.ChattingDetailScreen.route}/{${DetailDestinationKey.CHATTING}}",
        arguments = listOf(navArgument(DetailDestinationKey.CHATTING) { type = NavType.IntType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val chattingId = arguments.getInt(DetailDestinationKey.CHATTING)
        ChattingDetail(chattingId = chattingId, upPress)
    }

    composable(
        "${Screen.RecruitingDetailScreen.route}/{${DetailDestinationKey.RECRUITING}}",
        arguments = listOf(navArgument(DetailDestinationKey.RECRUITING) { type = NavType.IntType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val recruitingId = arguments.getInt(DetailDestinationKey.RECRUITING)
        RecruitingDetail(postId = recruitingId, upPress = upPress)
    }


    composable(
        "${Screen.TaggedReviewsScreen.route}/{${DetailDestinationKey.TAGGEDREVIEW}}",
        arguments = listOf(navArgument(DetailDestinationKey.TAGGEDREVIEW) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val taggedReviewId = arguments.getString(DetailDestinationKey.TAGGEDREVIEW)
        TaggedReviewsScreen(
            Tag = taggedReviewId!!,
            navController = navController,
            onReviewSelected = { id -> onReviewClick(id, backStackEntry)},
            onTagClick = { id -> onTagClick(id, backStackEntry)})
    }
}


private fun NavGraphBuilder.MainNavGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Int, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
){
    // nav bar routes
    composable(route = Screen.RecruitingsScreen.route) { from ->
        RecruitingsScreen(
            navController,
            onReviewSelected = {id -> onReviewClick(id, from)},
            onTagClick = { id -> onTagClick(id, from)},
            onRecruitingClick = { id -> onRecruitingClick(id, from)}
        )
    }
    composable(route = Screen.ReviewsScreen.route) { from ->
        Reviews(navController, onReviewSelected = {id -> onReviewClick(id, from)}, onTagClick = {id -> onTagClick(id, from)})
    }
    composable(route = Screen.ChattingsScreen.route) { from ->
        Chattings(navController, onChattingSelected = {id -> onChattingClick(id, from)})
    }
    composable(route = Screen.MyPageScreen.route) {
        MyPageScreen(navController)
    }


    composable(route = Screen.PostRecruitingScreen.route) {
        PostRecruitingScreen(navController, upPress = upPress)
    }
    composable(route = Screen.UserFeedScreen.route) { from ->
        UserFeedScreen(navController, onReviewSelected = {id -> onReviewClick(id, from)},onTagClick = { id -> onTagClick(id, from)})
    }
    composable(route = Screen.FollowingsScreen.route) { from ->
        FollowingsScreen(navController, upPress, onReviewSelected = {id -> onReviewClick(id, from)}, onTagClick = { id -> onTagClick(id, from)})
    }

}