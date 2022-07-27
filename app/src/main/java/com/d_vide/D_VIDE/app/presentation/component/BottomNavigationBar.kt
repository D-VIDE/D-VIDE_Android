package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.d_vide.D_VIDE.app.presentation.navigation.Screen

enum class BottomSections(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    DIVIDE("D/VIDE", Icons.Default.Home, Screen.RecruitingsScreen.route),
//    REVIEW("REVIEW", Icons.Default.Search, Screen.ReviewsScreen.route),

    // TESTING ROUTE
    REVIEW("REVIEW", Icons.Default.Search, Screen.TaggedReviewsScreen.route),

    CHATTING("CHATTING", Icons.Default.Message, Screen.ChattingsScreen.route),
    PROFILE("PROFILE", Icons.Default.AccountCircle, Screen.MyPageScreen.route)
}

@Composable
fun BottomNavigationBar(
    tabs: Array<BottomSections>,
    currentRoute: String,
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .shadow(elevation = 10.dp,
                shape = RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp),
                clip = true),
        backgroundColor = Color.White
    ) {
        Spacer(modifier = Modifier.padding(start = 30.dp))
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = BottomSections.DIVIDE.icon,
                    contentDescription = BottomSections.DIVIDE.title,
                    tint = Color.Gray
                )
            },
            onClick = {
                navController.navigate(BottomSections.DIVIDE.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = BottomSections.REVIEW.icon,
                    contentDescription = BottomSections.REVIEW.title,
                    tint = Color.Gray
                )
            },
            onClick = {
                navController.navigate(BottomSections.REVIEW.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = BottomSections.CHATTING.icon,
                    contentDescription = BottomSections.CHATTING.title,
                    tint = Color.Gray
                )
            },
            onClick = {
                navController.navigate(BottomSections.CHATTING.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = BottomSections.PROFILE.icon,
                    contentDescription = BottomSections.PROFILE.title,
                    tint = Color.Gray
                )
            },
            onClick = {
                navController.navigate(BottomSections.PROFILE.route)
            }
        )
        Spacer(modifier = Modifier.padding(start = 30.dp))
    }
}

@Preview
@Composable
fun BottomBarPreview() {
//    BottomNavigationBar(navController)
}