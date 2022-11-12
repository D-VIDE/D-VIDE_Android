package com.d_vide.D_VIDE.app.presentation.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.MyPage.MyPageScreen
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.UserFeed.UserProfileViewModel
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.util.GradientCompponent
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.gray1_1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyFollowScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    isFollowing: Boolean = false
) {
    val viewModel = hiltViewModel<FollowViewModel>()
    val follows = viewModel.state.value.follows
    val userViewModel = hiltViewModel<UserProfileViewModel>()
    val coroutine = rememberCoroutineScope()
    val userId = rememberSaveable{ mutableStateOf(0L) }

    coroutine.launch {
        viewModel.getFollowInfo(relation = if(isFollowing) "FOLLOWING" else "FOLLOWER")
    }
    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userId = userId.value
    ) { state, scope ->
        Scaffold(
            topBar = { TopRoundBar(if (isFollowing) "팔로잉" else "팔로우", onClick = upPress) }
        ) {
            Box(
                modifier = Modifier.fillMaxHeight()
            ) {
                LazyColumn(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp)
                ) {
                    follows.forEach { item ->
                        item {
                            FollowingItem(
                                userName = item.nickname,
                                profileUrl = item.profileImageUrl,
                                modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                                onUserClick = {
                                    userId.value = item.userId
                                    scope.launch {
                                        userViewModel.getOtherUserInfo(item.userId)
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                },
                                isFollowing = isFollowing,
                                userId = item.userId,
                                followId = item.followId,
                            )
                        }
                    }
                }
                GradientCompponent(Modifier.align(Alignment.BottomCenter))

                it
            }
        }
    }
}
