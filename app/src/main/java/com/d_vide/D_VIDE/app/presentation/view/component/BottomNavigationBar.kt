package com.d_vide.D_VIDE.app.presentation.view.component

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.navigation.NavGraph
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import kotlinx.coroutines.selects.select

enum class BottomSections(
    val title: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    val route: String
) {
    DIVIDE("디바이드", R.drawable.icon_divide, R.drawable.icon_selected_divide, Screen.RecruitingsScreen.route),
    REVIEW("리뷰", R.drawable.icon_review, R.drawable.icon_selected_review, Screen.ReviewsScreen.route),
    CHATTING("채팅", R.drawable.icon_chatting, R.drawable.icon_selected_chatting, Screen.ChattingsScreen.route),
    PROFILE("MY", R.drawable.icon_profile, R.drawable.icon_selected_profile, Screen.MyPageScreen.route)
}

@Composable
fun BottomNavigationBar(
    tabs: Array<BottomSections>,
    currentDestination: NavDestination?,
    navigationToRoute: (String) -> Unit,
    navController: NavController
) {


    BottomNavigation(
        modifier = Modifier
            .height(Const.UIConst.HEIGHT_BOTTOM_BAR)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp),
                clip = true
            ),
        backgroundColor = Color.White
    ) {
        Spacer(modifier = Modifier.padding(start = 30.dp))

        tabs.forEach { screen ->

            val selected = navController.backQueue.any { it.destination.route == screen.route }

            BottomNavigationItem(
                modifier = Modifier.padding(bottom = 5.dp),
                selected = selected,
                label = {
                    Text(
                        screen.title,
                        fontSize = 12.sp
                    )
                },
                icon = {
                    Column() {
                        Image(
                            painterResource(id = if(navController.backQueue.any { it.destination.route == screen.route }) screen.selectedIcon else screen.icon),
                            contentDescription = screen.title,
                            modifier = Modifier.size(27.dp)
                        )
                        Spacer(modifier = Modifier.size(7.dp))
                    }
                },
                onClick = bottomNavigate(section = screen, navigateToRoute = navigationToRoute)
            )
        }

        Spacer(modifier = Modifier.padding(end = 30.dp))
    }
}

@Composable
private fun bottomNavigate(
    section: BottomSections,
    navigateToRoute: (String) -> Unit
) = if (section == BottomSections.PROFILE) {
    { navigateToRoute(NavGraph.MYPAGE) }
} else {
    { navigateToRoute(section.route) }
}


@Preview
@Composable
fun BottomBarPreview() {
    //BottomNavigationBar(BottomSections.values(),Screen.RecruitingsScreen.route, rememberNavController())
}