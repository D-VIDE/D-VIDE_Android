package com.d_vide.D_VIDE.app.presentation.Reviews

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
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
        onReviewSelected = onReviewSelected
    ) { state, scope ->
        Scaffold(
            topBar = { TopRoundBar("D/VIDE맛집") },
        ){
            LazyColumn(
                modifier = Modifier.padding(end = 20.dp),
                contentPadding = PaddingValues(top = 28.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    RecommendRow(onTagClick)
                }
                item { ReviewItem(
                    onClick = {
                        scope.launch {
                            state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                        }
                    },
                    onReviewClick = {onReviewSelected(1234)}
                ) }
                item { ReviewItem(
                    onClick = {
                        scope.launch {
                            state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                        }
                    },
                    onReviewClick = {onReviewSelected(1234)}
                ) }
                item { ReviewItem(
                    onClick = {
                        scope.launch {
                            state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                        }
                    },
                    onReviewClick = {onReviewSelected(1234)}
                ) }

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

