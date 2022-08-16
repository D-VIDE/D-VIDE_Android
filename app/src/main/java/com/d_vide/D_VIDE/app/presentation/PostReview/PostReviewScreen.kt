package com.d_vide.D_VIDE.app.presentation.PostReview

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
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.*
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
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
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PostReviewViewModel.UiEvent.ShowSnackbar -> {
                    "post 성공".log()
                    navController.navigateUp()
                }
                is PostReviewViewModel.UiEvent.SaveReview -> "post 과정에서 error 발생".log()
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
                    navController.navigate(Screen.ReviewsScreen.route)
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
            EditableFieldItem(labelText = "내용", height = 200.dp) {
                EditableTextField(
                    height = 200.dp,
                    singleLine = false,
                    inputText = viewModel.reviewBodyDTO.value.content ?: "",
                    onValueChange = { viewModel.onEvent(PostReviewEvent.EnteredContent(it)) }) {}
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
        it
    }
}