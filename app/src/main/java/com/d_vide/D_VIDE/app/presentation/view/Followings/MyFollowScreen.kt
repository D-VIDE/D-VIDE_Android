package com.d_vide.D_VIDE.app.presentation.view.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserProfileViewModel
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.app.presentation.view.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.view.component.BlankIndicator
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyFollowScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    isFollowing: Boolean = false,
    followViewModel: FollowViewModel = hiltViewModel(),
) {
    val userViewModel = hiltViewModel<UserProfileViewModel>()
    val userId = rememberSaveable{ mutableStateOf(0L) }
    val viewModelState  by  followViewModel.state.collectAsState()
    val follows = viewModelState.follows
    val endReached =  viewModelState.endReached
    val pagingLoading = viewModelState.pagingLoading
    var relation = ""


    LaunchedEffect(key1 = true) {
        relation = if(isFollowing) "FOLLOWING" else "FOLLOWER"
        followViewModel.getFollowInfo(relation)
    }

    BottomSheetUserFeedScreen(
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
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp)
                ) {
                    itemsIndexed(follows) { index, item ->
                        if (index >= follows.size - 1 && !endReached && !pagingLoading) {
                            followViewModel.getFollowInfo(relation)
                        }
                        FollowingItem(
                            userName = item.nickname,
                            profileUrl = item.profileImgUrl,
                            modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                            onUserClick = {
                                userId.value = item.userId
                                userViewModel.getOtherUserInfo(item.userId)
                                scope.launch {
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

                    if(follows.isEmpty()) {
                        item {
                            BlankIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = "회원님이 팔로우하는 사람들이\n 여기에 표시됩니다."
                            )
                        }
                    }
                    item { Spacer(Modifier.padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)) }
                }
                GradientComponent(Modifier.align(Alignment.BottomCenter))

                it
            }
        }
    }
}
