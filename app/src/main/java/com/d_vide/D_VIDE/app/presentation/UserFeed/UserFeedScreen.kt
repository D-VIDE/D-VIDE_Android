package com.d_vide.D_VIDE.app.presentation.UserFeed

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.domain.util.log
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
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    userId: Long = 0L
){
    val viewModel = hiltViewModel<UserProfileViewModel>()
    val userProfile by viewModel.userProfile

    Box(){
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(main_gray2)
                .background(profile_gray)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            UserProfile(
                onFollowingClick = { navController.navigate(Screen.FollowingScreen.route) },
                onFollowerClick  = { navController.navigate(Screen.FollowerScreen.route) },
                userName = userProfile.userProfile.nickname,
                userBadge = if (!userProfile.userProfile.badges.isNullOrEmpty()) userProfile.userProfile.badges!!.get(0) else "",
                following = userProfile.userProfile.followingCount,
                follower = userProfile.userProfile.followerCount
            )
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .padding(bottom = 8.dp)
                    .height(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(start = 13.dp)
                        .padding(end = 9.dp)
                        .size(4.dp)
                ) {
                    drawCircle(Black)
                }
                Text(
                    text = "룡룡님 피드",
                    style = TextStyles.Basics4,
                    modifier = Modifier.weight(1f)
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                //  contentPadding = PaddingValues(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { ReviewItem(onReviewClick = {onReviewSelected(1234)}, onTagClick = {onTagClick("test")}, isliked = false) }

            }
        }
        GradientCompponent(Modifier.align(Alignment.BottomCenter))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetUserFeedSreen(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    userId: Long = 0L,
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
){
    "유저 아이디 ${userId}".log()
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
fun Preview() {
   //UserFeedScreen(rememberNavController())
}