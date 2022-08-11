package com.d_vide.D_VIDE.app.presentation.component

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.UIConst
import com.d_vide.D_VIDE.app.presentation.navigation.Screen

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
    currentRoute: String,
    navigationToRoute: (String) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .height(UIConst.UIConstant.HEIGHT_BOTTOM_BAR)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp),
                clip = true
            ),
        backgroundColor = Color.White
    ) {
        Spacer(modifier = Modifier.padding(start = 30.dp))

        tabs.forEach {
            BottomNavigationItem(
                modifier = Modifier.padding(bottom = 5.dp),
                selected = currentRoute == it.route,
                label = {
                    Text(
                        it.title,
                        fontSize = 12.sp
                    )
                },
                icon = {
                    Column() {
                        Image(
                            painterResource(id = if(currentRoute == it.route) it.selectedIcon else it.icon),
                            contentDescription = it.title,
                            modifier = Modifier.size(27.dp)
                        )
                        Spacer(modifier = Modifier.size(7.dp))
                    }
                },
                onClick = { navigationToRoute(it.route) }
            )
        }

        Spacer(modifier = Modifier.padding(end = 30.dp))
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    //BottomNavigationBar(BottomSections.values(),Screen.RecruitingsScreen.route, rememberNavController())
}