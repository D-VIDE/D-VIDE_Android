package com.d_vide.D_VIDE.app.presentation.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.MyPage.MyPageScreen
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.ui.theme.TextStyles
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FollowingsScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit
){
    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected
    ) { state, scope ->
        Scaffold(
            topBar = { TopRoundBar("팔로잉", onClick = upPress) }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(bottom = 10.dp)
                        .height(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Canvas(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .padding(end = 10.dp)
                            .size(4.dp)
                    ) {
                        drawCircle(Color.Black)
                    }
                    Text(
                        text = "맞팔로잉 하는 계정",
                        style = TextStyles.Basics4
                    )
                }
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .padding(start = 23.dp)
                        .padding(end = 17.dp),
                    color = Color(0xFFECECEC)
                )
                LazyColumn(
                    modifier = Modifier.align(CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp)
                ) {
                    for (i in 1..3) {
                        item {
                            FollowingItem(
                                modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                                onClick = {
                                    scope.launch {
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                }
                            )
                        }
                    }
                    item {
                        Box {
                            Divider(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .padding(start = 23.dp)
                                    .padding(end = 17.dp),
                                color = Color(0xFFECECEC)
                            )
                        }
                    }
                    for (i in 1..7) {
                        item {
                            FollowingItem(
                                modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                                onClick = {
                                    scope.launch {
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
            it
        }
    }
}
