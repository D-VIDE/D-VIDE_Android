package com.d_vide.D_VIDE.app.presentation.Reviews

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.RecruitingWriteButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBarWithImage
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.GradientCompponent
import com.d_vide.D_VIDE.ui.theme.gray6
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Reviews(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
){

    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick
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
                modifier = Modifier.fillMaxHeight().padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)
            ) {
                Box() {
                    LazyColumn(
                        contentPadding = PaddingValues(top = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        item {
                            RecommendRow(onTagClick)
                        }
                        items(10) {
                            ReviewItem(
                                onUserClick = {
                                    scope.launch {
                                        state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                                    }
                                },
                                onReviewClick = {onReviewSelected(1234)},
                                onTagClick = {onTagClick("test")}
                            )
                        }
                        item { Spacer(modifier = Modifier.size(it.calculateBottomPadding())) }
                    }
                    GradientCompponent(Modifier.align(Alignment.BottomCenter))
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

