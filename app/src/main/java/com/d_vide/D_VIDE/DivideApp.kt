package com.d_vide.D_VIDE

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.DivideScaffold
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.navigation.divideGraph
import com.d_vide.D_VIDE.ui.theme.DVIDETheme

@Composable
fun DivideApp() {
    val appState = rememberDivideAppState()

    DVIDETheme {
        DivideScaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                if(appState.shouldShowBottomBar){
                    BottomNavigationBar(
                        tabs = appState.bottomBarTabs,
                        currentDestination = appState.currentDestination,
                        navigationToRoute = appState::navigateToBottomBarRoute,
                        navController = appState.navController
                    )
                }
            },
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.SplashScreen.route,
            ) {
                divideGraph(
                    navController = appState.navController,
                    upPress = appState::upPress,
                    onReviewClick = appState::navigateToReview,
                    onChattingClick = appState::navigateToChatting,
                    onTagClick = appState::navigateToTaggedReview,
                    onRecruitingClick = appState::navigateToRecruitingDetail
                )
            }

        }

    }
}


