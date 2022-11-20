package com.d_vide.D_VIDE.app.presentation.view.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserFeedViewModel
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.app.presentation.view.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtherFollowScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    isFollowing: Boolean = false,
    userId: Long = 0,
    userViewModel: UserFeedViewModel = hiltViewModel(),
    followViewModel: FollowViewModel = hiltViewModel(),
) {
    val otherFollows = followViewModel.state.value.otherFollows
    val coroutine = rememberCoroutineScope()
    val userRememberId = rememberSaveable{ mutableStateOf(0L) }

    coroutine.launch {
        followViewModel.getOtherFollow(relation = if(isFollowing) "FOLLOWING" else "FOLLOWER", userId = userId)
    }
    BottomSheetUserFeedScreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userId = userRememberId.value
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
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                ) {
                    otherFollows.forEach { item ->
                        item {
                            FollowingItem(
                                userName = item.nickname,
                                profileUrl = item.profileImgUrl,
                                modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                                onUserClick = {
                                    userRememberId.value = item.userId
                                    scope.launch {
                                        userViewModel.getOtherUserInfo(item.userId)
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                },
                                isFollowing = true,
                                userId = item.userId,
                                followId = item.followId,
                                followed = item.followed
                            )
                        }
                    }
                }
                GradientComponent(Modifier.align(Alignment.BottomCenter))

                it
            }
        }
    }
}
