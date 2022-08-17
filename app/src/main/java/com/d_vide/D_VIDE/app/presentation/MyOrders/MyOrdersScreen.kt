@file:OptIn(ExperimentalMaterialApi::class)

package com.d_vide.D_VIDE.app.presentation.MyOrders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.MyOrders.component.MyCompletedOrder
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundTextContainer
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.*
import com.d_vide.D_VIDE.ui.theme.background

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyOrdersScreen(
    navController: NavController,
    viewModel: MyOrdersViewModel = hiltViewModel(),
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    onRecruitingClick: (Int) -> Unit
) {
    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick
    ) { state, scope ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(background)
            ) {
                TopRoundTextContainer("주문내역")

                LazyColumn(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = 13.dp, bottom = 150.dp)
                ) {
                    item { Spacer(modifier = Modifier.width(9.dp)) }

                    viewModel.state.value.recruitingDTOs.forEach {
                        item {
                            MyCompletedOrder(
                                onClick = { onRecruitingClick(it.post.id) },
                                onButtonClick = { navController.navigate(Screen.PostReviewScreen.route) },
                                title = it.post.title,
                                imageURL = it.user.profileImgUrl,
                                price = it.post.orderedPrice,
                                time = it.post.targetTime.convertTimestampToHour()
                                    .toString() + ":"
                                        + it.post.targetTime.convertTimestampToMinute()
                                    .toString(),
                                date = (it.post.targetTime * 1000).convertTimestampToPointFullDate(),
                                enabled = it.post.status == "RECRUIT_SUCCESS"
                            )
                        }
                    }
                }
            }
            GradientCompponent(Modifier.align(Alignment.BottomCenter))
        }
    }
}
