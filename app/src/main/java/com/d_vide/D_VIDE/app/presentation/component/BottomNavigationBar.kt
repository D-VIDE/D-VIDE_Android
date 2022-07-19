package com.d_vide.D_VIDE.app.presentation.component

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.Purple700
import java.util.*

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
//    val currentRoute = currentRoute(navController = navController)
//    val context = LocalContext.current

    BottomNavigation(
        modifier = Modifier.height(48.dp),
    ) {
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Home,
                    contentDescription = "",
                )
            },
            onClick = {
                navController.navigate(Screen.RecruitingsScreen.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                )
            },
            onClick = {
                navController.navigate(Screen.ReviewsScreen.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Message,
                    contentDescription = "",
                )
            },
            onClick = {
                navController.navigate(Screen.ChattingsScreen.route)
            }
        )
        BottomNavigationItem(
            selected = true,
            icon = {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "",
                )
            },
            onClick = {
                navController.navigate(Screen.MyPageScreen.route)
            }
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    var navController: NavController = rememberNavController()
    BottomNavigationBar(navController)
}