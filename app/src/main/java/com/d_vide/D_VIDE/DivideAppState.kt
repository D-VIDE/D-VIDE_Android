package com.d_vide.D_VIDE

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.component.BottomSections
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.navigation.shouldNotShowBottomScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun rememberDivideAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember(scaffoldState, navController, coroutineScope) {
    DivideAppState(scaffoldState, navController, coroutineScope)
}

class DivideAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {}
    }


    val currentDestination: NavDestination?
        get() = navController.currentDestination

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(Screen.HomeScreen.route) {
                    saveState = true
                }
            }
        }
    }


    fun navigateToReview(reviewId: Int, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${Screen.ReviewDetailScreen.route}/$reviewId")
        }
    }

    fun navigateToChatting(chattingId: Long, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${Screen.ChattingDetailScreen.route}/$chattingId")
        }
    }

    fun navigateToTaggedReview(taggedReviewId: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${Screen.TaggedReviewsScreen.route}/$taggedReviewId")
        }
    }

    fun navigateToRecruitingDetail(recruitingId: Int, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${Screen.RecruitingDetailScreen.route}/$recruitingId")
        }
    }

    // Screen 에 등록된 모든 route
    // val screens = Screen::class.sealedSubclasses.mapNotNull{ it.objectInstance }.map{ it.route }

    // BotNavBar 에 존재하는 routes
    val bottomBarTabs = BottomSections.values()
    private val notShowBottomBarRoutes = shouldNotShowBottomScreen

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route !in notShowBottomBarRoutes

    fun upPress() { navController.navigateUp() }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
