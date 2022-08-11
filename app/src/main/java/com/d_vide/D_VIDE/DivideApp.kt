package com.d_vide.D_VIDE

import androidx.compose.runtime.Composable
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.DivideScaffold
import com.d_vide.D_VIDE.app.presentation.navigation.NavGraph
import com.d_vide.D_VIDE.ui.theme.DVIDETheme

@Composable
fun DivideApp() {
    val appState = rememberDivideAppState()

    DVIDETheme {
        DivideScaffold(
            scaffoldState = appState.scaffoldState,
            bottomBar = {
                if(appState.shouldShowBottomBar) {
                    BottomNavigationBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigationToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
        ) {
            NavGraph(
                navController = appState.navController,
                upPress = appState::upPress,
            )
        }

    }
}


