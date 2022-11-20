package com.d_vide.D_VIDE.app.presentation.view.Reviews

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.Reviews.RecommendRow
import com.d_vide.D_VIDE.app.presentation.view.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserFeedViewModel
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserProfileViewModel
import com.d_vide.D_VIDE.app.presentation.view.component.RecruitingWriteButton
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBarWithImage
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.ui.theme.gray6
import kotlinx.coroutines.launch

/**
 * reviewId Long으로 바뀌었으니 함수 수정 해야함
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Reviews(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    userFeedViewModel: UserFeedViewModel = hiltViewModel(),
){
    val userId = rememberSaveable{ mutableStateOf(0L) }
    val viewModel = hiltViewModel<ReviewsViewModel>()
    val userViewModel = hiltViewModel<UserProfileViewModel>()
    val reviews = viewModel.state.value.reviews
    val recommend = viewModel.state.value.recommendStore

    BottomSheetUserFeedScreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userId = userId.value
    ) { state, scope ->
        Scaffold(
            topBar = { TopRoundBarWithImage() },
            floatingActionButton = {
                RecruitingWriteButton(
                    onClick = { navController.navigate(Screen.PostReviewScreen.route) },
                    shouldShowBottomBar = true
                )
            }
        ){
            Surface(
                color = gray6,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)
            ) {
                Box() {
                    LazyColumn(
                        contentPadding = PaddingValues(top = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        item {
                            RecommendRow(onTagClick, recommend)
                        }
                        itemsIndexed(reviews){ index, item ->
                            ReviewItem(
                                onUserClick = {
                                    userId.value = item.user.id
                                    scope.launch {
                                        userFeedViewModel.getOtherUserInfo(item.user.id)
                                        state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                                    }
                                },
                                onReviewClick = {onReviewSelected(item.review.reviewId.toInt())},
                                onTagClick = {onTagClick(item.review.storeName)},
                                onLikeClick = {if(item.review.isLiked) viewModel.postUnlike(index) else viewModel.postLike(index)},
                                isLiked = item.review.isLiked,
                                userImageURL = item.user.profileImgUrl,
                                userName = item.user.nickname,
                                reviewTitle = item.review.storeName,
                                reviewText = item.review.content,
                                reviewImage = item.review.reviewImgUrl
                            )
                        }
                        item { Spacer(modifier = Modifier.size(it.calculateBottomPadding())) }
                    }
                    GradientComponent(Modifier.align(Alignment.BottomCenter))
                }
            }
            it
        }
        BackHandler (
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded ||
                    state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch{
                    state.animateTo(ModalBottomSheetValue.Hidden, tween(300))
                }
            }
        )
    }

}

