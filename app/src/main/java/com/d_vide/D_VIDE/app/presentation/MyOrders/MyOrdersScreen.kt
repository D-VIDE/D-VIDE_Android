@file:OptIn(ExperimentalMaterialApi::class)

package com.d_vide.D_VIDE.app.presentation.MyOrders

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.Recruitings.BlankIndicator
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundTextContainer
import com.d_vide.D_VIDE.app.presentation.util.GradientCompponent
import com.d_vide.D_VIDE.app.presentation.util.LocationConverter
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToHour
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToMinute
import com.d_vide.D_VIDE.ui.theme.background
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

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
                            RecruitingItem(
                                onUserClick = {
                                    scope.launch {
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                },
                                onClick = { onRecruitingClick(it.post.id) },
                                userName = it.user.nickname,
                                userLocation = LocationConverter(
                                    LatLng(
                                        it.post.latitude,
                                        it.post.longitude
                                    )
                                ),
                                title = it.post.title,
                                imageURL = it.user.profileImgUrl,
                                insufficientMoney =
                                if (it.post.targetPrice > it.post.orderedPrice)
                                    it.post.targetPrice - it.post.orderedPrice
                                else 0,
                                timeRemaining = ((it.post.targetTime - System.currentTimeMillis() / 1000) / 60),
                                deadLineHour = it.post.targetTime.convertTimestampToHour(),
                                deadLineMinute = it.post.targetTime.convertTimestampToMinute()
                            )
                        }
                    }
                    item {
                        BlankIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 78.dp)
                        )
                    }
                }
            }
            GradientCompponent(Modifier.align(Alignment.BottomCenter))
        }
    }
}