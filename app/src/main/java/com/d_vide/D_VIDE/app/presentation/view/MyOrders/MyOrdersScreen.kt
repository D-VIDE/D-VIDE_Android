@file:OptIn(ExperimentalMaterialApi::class)

package com.d_vide.D_VIDE.app.presentation.view.MyOrders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.view.MyOrders.component.MyCompletedOrder
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.*
import com.d_vide.D_VIDE.app.presentation.view.component.BlankIndicator
import com.d_vide.D_VIDE.ui.theme.background

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyOrdersScreen(
    navController: NavController,
    viewModel: MyOrdersViewModel = hiltViewModel(),
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    onRecruitingClick: (Int) -> Unit
) {
    val viewModelState  by  viewModel.state.collectAsState()
    val recruitings = viewModelState.recruitings
    val endReached =  viewModelState.endReached
    val pagingLoading = viewModelState.pagingLoading


    Scaffold(
        topBar = { TopRoundBar("주문내역", onClick = upPress) }
    ) {
        BottomSheetUserFeedScreen(
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
                    LazyColumn(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(top = 13.dp, bottom = 150.dp)
                    ) {
                        item { Spacer(modifier = Modifier.width(9.dp)) }

                        itemsIndexed(recruitings) { index, item ->
                            if (index >= recruitings.size - 1 && !endReached && !pagingLoading) {
                                viewModel.getMyOrders()
                            }

                            MyCompletedOrder(
                                onClick = { onRecruitingClick(item.post.id) },
                                onButtonClick = { navController.navigate(Screen.PostReviewScreen.route) },
                                title = item.post.title,
                                imageURL = item.user.profileImgUrl,
                                price = item.post.orderedPrice,
                                time = item.post.targetTime.convertTimestampToHour()
                                    .toString() + ":"
                                        + item.post.targetTime.convertTimestampToMinute()
                                    .toString(),
                                date = (item.post.targetTime * 1000).convertTimestampToPointFullDate(),
                                enabled = item.post.status == "RECRUIT_SUCCESS"
                            )
                        }

                        if(recruitings.isEmpty()) {
                            item {
                                BlankIndicator(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(vertical = 78.dp)
                                )
                            }
                        }
                        item { Spacer(Modifier.padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)) }
                    }
                }
                GradientComponent(Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}
