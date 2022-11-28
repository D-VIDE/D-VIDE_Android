package com.d_vide.D_VIDE.app.presentation.navigation


import androidx.navigation.*
import androidx.navigation.compose.composable
import com.d_vide.D_VIDE.app.presentation.ChattingDetail.ChattingDetail
import com.d_vide.D_VIDE.app.presentation.SplashScreen
import com.d_vide.D_VIDE.app.presentation.view.Chattings.Chattings
import com.d_vide.D_VIDE.app.presentation.view.Followings.MyFollowScreen
import com.d_vide.D_VIDE.app.presentation.view.Followings.OtherFollowScreen
import com.d_vide.D_VIDE.app.presentation.view.Login.LoginScreen
import com.d_vide.D_VIDE.app.presentation.view.Login.SocialLoginScreen
import com.d_vide.D_VIDE.app.presentation.view.MyOrders.MyOrdersScreen
import com.d_vide.D_VIDE.app.presentation.view.MyPage.MyPageScreen
import com.d_vide.D_VIDE.app.presentation.view.MyReviews.MyReviewsScreen
import com.d_vide.D_VIDE.app.presentation.view.PostRecruiting.PostRecruitingScreen
import com.d_vide.D_VIDE.app.presentation.view.PostReview.PostReviewScreen
import com.d_vide.D_VIDE.app.presentation.view.RecruitingDetail.RecruitingDetailScreen
import com.d_vide.D_VIDE.app.presentation.view.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.app.presentation.view.ReviewDetail.ReviewDetail
import com.d_vide.D_VIDE.app.presentation.view.Reviews.Reviews
import com.d_vide.D_VIDE.app.presentation.view.TaggedReviews.TaggedReviewsScreen


fun NavGraphBuilder.divideGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Long, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
) {
    navigation(
        route = Screen.HomeScreen.route,
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
        route = Screen.SplashScreen.route
    ) {
        SplashScreen(navController)
    }

    composable(
        route = Screen.LoginScreen.route
    ) {
        LoginScreen(navController)
    }

    composable(
        route = Screen.SocialLoginScreen.route
    ) {
        SocialLoginScreen(navController)
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
            onTagClick = { id -> onTagClick(id, backStackEntry) })
    }

    composable(
        "${Screen.ChattingDetailScreen.route}/{${DetailDestinationKey.CHATTING}}",
        arguments = listOf(navArgument(DetailDestinationKey.CHATTING) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val chattingId = arguments.getInt(DetailDestinationKey.CHATTING)
        ChattingDetail(chattingId = chattingId, upPress = upPress)
    }

    composable(
        "${Screen.RecruitingDetailScreen.route}/{${DetailDestinationKey.RECRUITING}}",
        arguments = listOf(navArgument(DetailDestinationKey.RECRUITING) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val recruitingId = arguments.getInt(DetailDestinationKey.RECRUITING)
        RecruitingDetailScreen(
            postId = recruitingId,
            upPress = upPress,
            navController = navController,
            onChattingSelected = {id -> onChattingClick(id, backStackEntry)}
        )
    }


    composable(
        "${Screen.TaggedReviewsScreen.route}/{${DetailDestinationKey.TAGGEDREVIEW}}",
        arguments = listOf(navArgument(DetailDestinationKey.TAGGEDREVIEW) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val taggedReviewId = arguments.getString(DetailDestinationKey.TAGGEDREVIEW)
        TaggedReviewsScreen(
            Tag = taggedReviewId!!,
            navController = navController,
            onReviewSelected = { id -> onReviewClick(id, backStackEntry) },
            onTagClick = { id -> onTagClick(id, backStackEntry) })
    }

//    composable(route = Screen.UserFeedScreen.route) { from ->
//        UserFeedScreen(
//            navController,
//            onReviewSelected = { id -> onReviewClick(id, from) },
//            onTagClick = { id -> onTagClick(id, from) },
//            userId =
//        )
//    }

    composable(route = Screen.PostRecruitingScreen.route) {
        PostRecruitingScreen(navController, upPress = upPress)
    }
}


private fun NavGraphBuilder.MainNavGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Long, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
) {
    // nav bar routes
    composable(route = Screen.RecruitingsScreen.route) { from ->
        RecruitingsScreen(
            navController,
            onReviewSelected = { id -> onReviewClick(id, from) },
            onTagClick = { id -> onTagClick(id, from) },
            onRecruitingClick = { id -> onRecruitingClick(id, from) }
        )
    }
    composable(route = Screen.ReviewsScreen.route) { from ->
        Reviews(
            navController,
            onReviewSelected = { id -> onReviewClick(id, from) },
            onTagClick = { id -> onTagClick(id, from) })
    }
    composable(route = Screen.ChattingsScreen.route) { from ->
        Chattings(navController, onChattingSelected = { id -> onChattingClick(id, from) })
    }
    navigation(
        route = NavGraph.MYPAGE,
        startDestination = Screen.MyPageScreen.route
    ) {
        MyPageNavGraph(
            onReviewClick = onReviewClick,
            onChattingClick = onChattingClick,
            onTagClick = onTagClick,
            onRecruitingClick = onRecruitingClick,
            upPress = upPress,
            navController = navController
        )
    }


}

private fun NavGraphBuilder.MyPageNavGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Long, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
) {
    composable(route = Screen.MyPageScreen.route) {
        MyPageScreen(navController)
    }
    composable(route = "${Screen.MyFollowScreen.route}/{isFollowing}",
        arguments = listOf(navArgument("isFollowing") { type = NavType.BoolType })
    ) { from ->
        val isFollowing = from.arguments?.getBoolean("isFollowing") ?: false
        MyFollowScreen(
            navController,
            upPress,
            onReviewSelected = { id -> onReviewClick(id, from) },
            onTagClick = { id -> onTagClick(id, from) },
            isFollowing = isFollowing
        )
    }

    composable(route = "${Screen.OtherFollowScreen.route}/{isFollowing}/{userId}",
        arguments = listOf(navArgument("isFollowing"){type = NavType.BoolType},
        navArgument("userId"){type = NavType.LongType}
        )) { from ->
        val isFollowing = from.arguments?.getBoolean("isFollowing") ?: false
        val userId = from.arguments?.getLong("userId") ?: 0
        OtherFollowScreen(navController, upPress, onReviewSelected = {id -> onReviewClick(id, from)},
            onTagClick = { id -> onTagClick(id, from)}, isFollowing = isFollowing, userId = userId)
    }

    navigation(
        route = NavGraph.MYREVIEW,
        startDestination = Screen.MyOrdersScreen.route
    ) {
        MyReviewNavGraph(
            onReviewClick = onReviewClick,
            onChattingClick = onChattingClick,
            onTagClick = onTagClick,
            onRecruitingClick = onRecruitingClick,
            upPress = upPress,
            navController = navController
        )
    }
}

private fun NavGraphBuilder.MyReviewNavGraph(
    navController: NavController,
    upPress: () -> Unit,
    onReviewClick: (Int, NavBackStackEntry) -> Unit,
    onChattingClick: (Long, NavBackStackEntry) -> Unit,
    onTagClick: (String, NavBackStackEntry) -> Unit,
    onRecruitingClick: (Int, NavBackStackEntry) -> Unit
) {
    composable(
        route = Screen.MyOrdersScreen.route
    ) { from ->
        MyOrdersScreen(
            navController = navController,
            onReviewSelected = { id -> onReviewClick(id, from) },
            onTagClick = { id -> onTagClick(id, from) },
            onRecruitingClick = { id -> onRecruitingClick(id, from) }
        )
    }

    composable(
        route = Screen.MyReviewsScreen.route
    ) { from ->
        MyReviewsScreen(
            navController = navController,
            onReviewSelected = { id -> onReviewClick(id, from) },
            onTagClick = { id -> onTagClick(id, from) },
            upPress = upPress
        )
    }

    composable(route = Screen.PostReviewScreen.route) { from ->
        PostReviewScreen(navController, upPress, onReviewClick = { id -> onReviewClick(id, from) })
    }
}