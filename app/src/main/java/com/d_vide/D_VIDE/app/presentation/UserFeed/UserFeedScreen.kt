package com.d_vide.D_VIDE.app.presentation.UserFeed

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.component.UserProfile
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.GradientCompponent
import com.d_vide.D_VIDE.ui.theme.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun UserFeedScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    modifier: Modifier = Modifier,
    onReviewSelected: (Int) -> Unit = {},
    onTagClick: (String) -> Unit = {},
    viewModel: UserFeedViewModel = hiltViewModel(),
    userId: Long = 0L
) {
    val userProfile = viewModel.userProfile.userProfile

    UserFeedBackground(modifier) {
        UserFeedContent {
            UserProfile(
                onFollowingClick = {
                    navController.navigate("${Screen.OtherFollowScreen.route}/true/$userId")
                },
                onFollowerClick  ={
                    navController.navigate("${Screen.OtherFollowScreen.route}/false/$userId")
                },
                userName = userProfile.nickname,
                imageUrl = userProfile.profileImgUrl,
                userBadge = userProfile.badge.name,
                following = userProfile.followingCount,
                follower = userProfile.followerCount,
                followed = userProfile.followed,
                userId = userId
            )
            UserFeeds(
                onReviewSelected = onReviewSelected,
                onTagClick = onTagClick,
                upPress = upPress,
                userName = userProfile.nickname,
                imageUrl = userProfile.profileImgUrl,
                userId = userId,
            )
        }
    }
}

@Composable
fun ColumnScope.UserFeeds(
    onReviewSelected: (Int) -> Unit = {},
    onTagClick: (String) -> Unit = {},
    upPress: () -> Unit = {},
    userName: String,
    imageUrl: String,
    userId: Long,
) {
    UserFeedTitleText(userName)
    UserFeedList(
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userName = userName,
        imageUrl = imageUrl,
        userId = userId,
    )
}

@Composable
fun ColumnScope.UserFeedList(
    onReviewSelected: (Int) -> Unit = {},
    onTagClick: (String) -> Unit = {},
    userName: String,
    imageUrl: String,
    userId: Long,
    viewModel: UserReviewsViewModel = hiltViewModel()
) {
    viewModel.getOtherUserReviews(userId)
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(viewModel.userReviews.userReviews.reviews) { index, item ->
            ReviewItem(
                onUserClick = { },
                onReviewClick = { onReviewSelected(item.reviewId.toInt()) },
                onTagClick = { onTagClick(item.storeName) },
//                onLikeClick = {if(item.review.liked) viewModel.postUnlike(index) else viewModel.postLike(index)},
                // 추후 기능 추가 필요
                onLikeClick = {},
                isLiked = item.isLiked,
                userImageURL = imageUrl,
                userName = userName,
                reviewTitle = item.storeName,
                reviewText = item.content,
                reviewImage = item.reviewImgUrl
            )
        }
    }
}

@Composable
fun UserFeedTitleText(
    userName: String = "익명",
) {
    Row(
        modifier = Modifier
            .padding(top = 18.dp)
            .padding(bottom = 8.dp)
            .padding(start = 18.dp)
            .height(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "${userName}님 피드",
            style = TextStyles.Basics4,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun UserFeedContent(
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
            .padding(top = 18.dp)
    ) {
        content()
    }
}

@Composable
fun UserFeedBackground(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(main_gray2)
            .background(profile_gray)
            .padding(innerPadding),
    ) {
        content()
        GradientCompponent(Modifier.align(Alignment.BottomCenter))
    }
}


@Preview
@Composable
fun PreviewUserFeedScreen() {
    UserFeedScreen(
        navController = rememberNavController(),userId = 0L
    )
}