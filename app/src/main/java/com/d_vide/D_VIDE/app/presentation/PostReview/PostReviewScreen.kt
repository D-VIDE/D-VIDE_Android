package com.d_vide.D_VIDE.app.presentation.PostReview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.*
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.StarRating
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.navigation.NavGraph
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.addFocusCleaner
import com.d_vide.D_VIDE.ui.theme.background
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostReviewScreen(
    navController: NavController,
    upPress: () -> Unit = {}
){

    val viewModel = hiltViewModel<PostReviewViewModel>()
    val reviewId = viewModel.reviewId.value
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PostReviewViewModel.UiEvent.ShowSnackbar -> {
                    "post 실패".log()
                    navController.navigateUp()
                }
                is PostReviewViewModel.UiEvent.SaveReview -> {
                    navController.navigate(NavGraph.MYREVIEW){
                        popUpTo(NavGraph.MYREVIEW)
                    }
                    Log.d("test", "성공 & 뒤로가자")
                }
            }
        }
    }


    Scaffold(
        topBar = { TopRoundBar("후기작성", onClick = upPress) },
        floatingActionButton = {
            FloatingButton(
                text = "업로드 하기",
                onClick = {
                    viewModel.onEvent(PostReviewEvent.SaveReview)
                },
                shouldShowBottomBar = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(
                    scrollState
                )
                .background(background)
                .padding(horizontal = 20.dp)
                .addFocusCleaner(LocalFocusManager.current),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(bottom = 28.dp))


            // 가게이름
            EditableFieldItem(labelText = "가게이름") {
                EditableTextField(
                    inputText = viewModel.reviewBodyDTO.value.storeName ?: "",
                    onValueChange = { viewModel.onEvent(PostReviewEvent.EnteredStoreName(it)) }
                )
            }

            EditableFieldItem(labelText = "별점") {
                Box(modifier = Modifier.fillMaxWidth()){
                    StarRating(
                        modifier = Modifier.align(Alignment.Center),
                        rating = viewModel.reviewBodyDTO.value.starRating,
                        onValueChange = {viewModel.onEvent(PostReviewEvent.EnteredStarRating(it))}
                    )
                }
            }


            
            // 사진
            EditableFieldItem(labelText = "사진", height = 80.dp) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    viewModel.imageUris.forEachIndexed { idx, it ->
                        item {
                            PhotoPicker(
                                R.drawable.add_photo, it,
                                { viewModel.onEvent(PostReviewEvent.EnteredImage(it, idx)) },
                                { viewModel.onEvent(PostReviewEvent.DeleteImage(idx)) },
                                modifier = Modifier.size(77.dp,71.dp)
                            )
                        }
                    }
                    if (viewModel.imageUris.size < 3) item {
                        PhotoPicker(
                            iconId = R.drawable.add_photo,
                            onGetContent = {
                                viewModel.onEvent(
                                    PostReviewEvent.EnteredImage(it, -1)
                                )
                            },
                            modifier = Modifier.size(77.dp,71.dp)
                        )
                    }
                }
            }

            // 내용
            EditableFieldItem(labelText = "내용", height = 330.dp) {
                EditableTextField(
                    height = 330.dp,
                    singleLine = false,
                    inputText = viewModel.reviewBodyDTO.value.content ?: "",
                    onValueChange = { viewModel.onEvent(PostReviewEvent.EnteredContent(it)) }) {}
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
        it
    }
}