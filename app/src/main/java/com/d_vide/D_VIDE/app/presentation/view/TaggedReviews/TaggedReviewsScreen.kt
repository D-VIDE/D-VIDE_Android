package com.d_vide.D_VIDE.app.presentation.view.TaggedReviews

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.view.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserProfileViewModel
import com.d_vide.D_VIDE.app.presentation.view.component.RecruitingWriteButton
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaggedReviewsScreen(
    Tag: String,
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
) {

    val userViewModel = hiltViewModel<UserProfileViewModel>()
    val viewModel = hiltViewModel<TaggedReviewsViewModel>()
    val reviews = viewModel.state.value.reviews
    val userId = rememberSaveable{ mutableStateOf(0L) }
    BottomSheetUserFeedScreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userId = userId.value
    ) { state, scope ->
        Scaffold(
            floatingActionButton = {
                RecruitingWriteButton(
                    onClick = { navController.navigate(Screen.PostReviewScreen.route) },
                    shouldShowBottomBar = true
                )
            }
        ) {
            Box() {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(background)
                ) {
                    TagTitle(title = Tag)
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 28.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(reviews){ review ->
                            ReviewItem(
                                onUserClick = {
                                    userId.value = 1 // need to change
                                    scope.launch {
                                        userViewModel.getOtherUserInfo(1) // need to change userId
                                        state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                                    }
                                },
                                onReviewClick = { onReviewSelected(review.user.id.toInt()) },
                                onTagClick = { onTagClick(review.review.storeName) },
                                userImageURL = review.user.profileImgUrl,
                                userName = review.user.nickname,
                                isLiked = review.review.isLiked,
                                reviewImage = review.review.reviewImgUrl,
                                reviewText = review.review.content,
                                reviewTitle = review.review.storeName,
                                starRating = review.review.starRating
                            )
                        }


                    }
                }
                GradientComponent(Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}

@Composable
fun TagTitle(title: String = "금돼지 식당") {
    Surface(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .border(2.dp, main0, RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)),
        color = gray7,
        shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)
    ) {
        Text(
            text = "#$title",
            style = TextStyles.Big1,
            color = gray3,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun Preview() {
//    TaggedReviewsScreen(rememberNavController())
}