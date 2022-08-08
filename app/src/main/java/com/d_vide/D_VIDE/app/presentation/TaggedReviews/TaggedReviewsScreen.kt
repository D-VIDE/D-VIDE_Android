package com.d_vide.D_VIDE.app.presentation.TaggedReviews

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaggedReviewsScreen(
    navController: NavController
) {
    BottomSheetUserFeedSreen(
        navController = navController
    ) { state, scope ->
        Scaffold(
            floatingActionButton = {
                FloatingButton(
                    text = "지금 D/VIDE 하기",
                    onClick = { /*TODO*/ },
                    shouldShowBottomBar = true
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.background(background)
            ) {
                TagTitle()
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .weight(1f),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                    item { ReviewItem(
                        onClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        }
                    ) }
                }
            }
        }
    }
}

@Composable
fun TagTitle(title: String = "금돼지 식당") {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(3.dp, mainOrange, RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "#$title",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(vertical = 15.dp
            )
        )
    }
}

@Composable
fun TagBottomButton(
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = mainOrange)
    ) {
        Text("지금 D/VIDE 하기", color = Color.White)
    }
}

@Preview
@Composable
fun Preview() {
//    TaggedReviewsScreen(rememberNavController())
}