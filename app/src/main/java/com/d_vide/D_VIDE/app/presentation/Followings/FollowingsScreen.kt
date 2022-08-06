package com.d_vide.D_VIDE.app.presentation.Followings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar

@Composable
fun FollowingsScreen(
    navController: NavController,
    upPress: () -> Unit = {}
){
    Scaffold(
        topBar = { TopRoundBar("팔로잉", onClick = upPress) }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 33.dp)
                    .padding(bottom = 19.dp)
                    .height(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .padding(end = 9.dp)
                        .size(4.dp)
                ) {
                    drawCircle(Color.Black)
                }
                Text(
                    text = "맞팔로잉 하는 계정",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .padding(start = 9.dp)
                    .padding(end = 31.dp),
                color = Color(0xFFECECEC)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .align(CenterHorizontally)
            ){
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
                item { FollowingItem() }
            }
        }
        it
    }
}
