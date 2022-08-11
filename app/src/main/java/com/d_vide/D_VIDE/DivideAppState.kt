package com.d_vide.D_VIDE

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.component.BottomSections
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

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(BottomSections.DIVIDE.route) {
                    saveState = true
                }
            }
        }
    }

    // Screen 에 등록된 모든 route
    // val screens = Screen::class.sealedSubclasses.mapNotNull{ it.objectInstance }.map{ it.route }

    // BotNavBar 에 존재하는 routes
    val bottomBarTabs = BottomSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    fun upPress() { navController.navigateUp() }
}