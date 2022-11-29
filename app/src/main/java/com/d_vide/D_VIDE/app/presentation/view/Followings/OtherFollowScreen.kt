package com.d_vide.D_VIDE.app.presentation.view.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
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
import com.d_vide.D_VIDE.app.presentation.view.component.BlankIndicator
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
    val coroutine = rememberCoroutineScope()
    val userRememberId = rememberSaveable{ mutableStateOf(0L) }
    val viewModelState  by  followViewModel.state.collectAsState()
    val otherFollows = viewModelState.otherFollows
    val endReached =  viewModelState.endReached
    val pagingLoading = viewModelState.pagingLoading
    var relation = ""

    LaunchedEffect(key1 = true) {
        relation = if(isFollowing) "FOLLOWING" else "FOLLOWER"
        followViewModel.getOtherFollow(relation = relation, userId = userId)
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
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                ) {
                    itemsIndexed(otherFollows) { index, item ->
                        if (index >= otherFollows.size - 1 && !endReached && !pagingLoading) {
                            followViewModel.getOtherFollow(relation, userId)
                        }
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
                    if(otherFollows.isEmpty()) {
                        item {
                            BlankIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = "회원님을 팔로우하는 사람들이\n 여기에 표시됩니다."
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
