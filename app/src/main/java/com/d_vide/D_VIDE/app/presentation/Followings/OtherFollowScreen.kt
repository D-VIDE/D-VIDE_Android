package com.d_vide.D_VIDE.app.presentation.Followings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.Followings.components.FollowingItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.util.GradientCompponent
import kotlinx.coroutines.launch

// 다른 사람의 팔로잉, 팔로우 목록 보여줌
// api 다를 것 같아서 따로 뺐어요
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtherFollowScreen(
    navController: NavController,
    upPress: () -> Unit = {},
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    isFollowing: Boolean = false
) {
    BottomSheetUserFeedScreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick
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
                    for (i in 1..12){
                        item {
                            FollowingItem(
                                modifier = Modifier.padding(start = 33.dp, end = 40.dp),
                                onUserClick = {
                                    scope.launch {
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                },
                                isFollowing = isFollowing
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
