package com.d_vide.D_VIDE.app.presentation.UserFeed

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.d_vide.D_VIDE.app.presentation.Followings.FollowViewModel
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
    viewModel: UserProfileViewModel = hiltViewModel(),
    followViewModel: FollowViewModel = hiltViewModel(),
    userId: Long = 0L
) {
    val userProfile by viewModel.userProfile

    UserFeedBackground(modifier) {
        UserFeedContent {
            UserProfile(
                onFollowingClick = { navController.navigate("${Screen.OtherFollowScreen.route}/true") },
                onFollowerClick = { navController.navigate("${Screen.OtherFollowScreen.route}/false") },
                userName = userProfile.userProfile.nickname,
                userBadge =
                if (!userProfile.userProfile.badges.isNullOrEmpty()) userProfile.userProfile.badges!!.get(
                    0
                )
                else "",
                following = userProfile.userProfile.followingCount,
                follower = userProfile.userProfile.followerCount,
                FollowingButton = { followViewModel.postFollow(2) }
            )
            UserFeeds(
                onReviewSelected = onReviewSelected,
                onTagClick = onTagClick,
                upPress = upPress,
                userName = "룡룡"
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

) {
    UserFeedTitleText(userName)
    UserFeedList(
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
    )
}

@Composable
fun ColumnScope.UserFeedList(
    onReviewSelected: (Int) -> Unit = {},
    onTagClick: (String) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(3) {
            item {
                ReviewItem(
                    onReviewClick = { onReviewSelected(1234) },
                    onTagClick = { onTagClick("test") },
                    isliked = false
                )
            }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetUserFeedScreen(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    userId: Long = 0L,
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
) {
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetState = state,
        sheetContent = {
            UserFeedScreen(
                navController = navController,
                modifier = Modifier.fillMaxHeight(0.945f),
                onReviewSelected = onReviewSelected,
                onTagClick = onTagClick,
                userId = userId
            )
        }
    ) {
        activityContentScope(state, scope)
    }
}


@Preview
@Composable
fun PreviewUserFeedScreen() {
    UserFeedScreen(
        navController = rememberNavController(),
    )
}