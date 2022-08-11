package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._enums.Category
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
fun PostRecruitingScreen(
    navController: NavController,
    viewModel: PostRecruitingViewModel = hiltViewModel(),
    upPress: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is PostRecruitingViewModel.UiEvent.ShowSnackbar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
                    Log.d("test", "뭔가 문제")
                    navController.navigateUp()
                }
                is PostRecruitingViewModel.UiEvent.SaveRecruiting -> {
                    Log.d("test", "성공 & 뒤로가자")

                }
            }
        }
    }

    Scaffold(
        topBar = { TopRoundBar("D/VIDE 모집글 작성", onClick = upPress) },
        floatingActionButton = {
            FloatingButton(
                text = "업로드 하기",
                onClick = {
                    viewModel.onEvent(PostRecruitingsEvent.SaveRecruiting)
                    navController.navigate(Screen.RecruitingsScreen.route)
                },
                shouldShowBottomBar = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(
                    scrollState,
                    enabled = !viewModel.cameraPositionState.value.isMoving
                )
                .background(background)
                .padding(horizontal = 20.dp)
                .addFocusCleaner(LocalFocusManager.current),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(bottom = 28.dp))

            // 제목
            EditableFieldItem(labelText = "제목") {
                EditableTextField(
                    inputText = viewModel.recruitingBodyDTO.value.title ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredTitle(it)) }
                )
            }

            // 가게이름
            EditableFieldItem(labelText = "가게이름") {
                EditableTextField(
                    inputText = viewModel.recruitingBodyDTO.value.storeName ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredStoreName(it)) }
                )
            }

            // 카테고리
            Box {
                if (isDropDownMenuExpanded) {
                    var selectedCategory: Category = Category.ALL
                    ExpandedCategory(
                        onTagClick = {
                            selectedCategory = it
                            isDropDownMenuExpanded = !isDropDownMenuExpanded
                            selectedText = selectedCategory.name
                            viewModel.onEvent(PostRecruitingsEvent.EnteredCategory(selectedCategory))
                        },
                        currentTag = selectedCategory
                    )
                }
                EditableFieldItem(labelText = "카테고리") {
                    DropDownComp(
                        isDropDownMenuExpanded = isDropDownMenuExpanded,
                        onCheckedChange = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
                        selectedText = selectedText
                    )
                }
            }

            // 배달비
            EditableFieldItem(labelText = "배달비") {
                EditableTextField(
                    unitText = "원",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    inputText = viewModel.recruitingBodyDTO.value.deliveryPrice?.toString() ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredDeliveryPrice(if (it.isNullOrBlank()) null else it.toInt())) }
                )
            }

            // 목표 금액
            EditableFieldItem(labelText = "목표 금액") {
                EditableTextField(
                    unitText = "원",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    inputText = viewModel.recruitingBodyDTO.value.targetPrice?.toString() ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredTargetPrice(if (it.isNullOrBlank()) null else it.toInt())) }
                )
            }

            // 마감시간
            EditableFieldItem(labelText = "마감시간") { TimePicker() }

            // 사진
            EditableFieldItem(labelText = "사진", height = 80.dp) {
                LazyRow() {
                    item { PhotoPicker(iconId = R.drawable.add_photo) }
                    item { PhotoPicker(iconId = R.drawable.add_photo) }
                }
            }

            // 장소
            EditableFieldItem(labelText = "장소", height = 200.dp) {
                LocationSelector(viewModel.cameraPositionState.value)
            }

            // 내용
            EditableFieldItem(labelText = "내용", height = 200.dp) {
                EditableTextField(
                    height = 200.dp,
                    singleLine = false,
                    inputText = viewModel.recruitingBodyDTO.value.content ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredContent(it)) }) {}
            }
            Spacer(modifier = Modifier.padding(50.dp))
        }
        it
    }
}

@Preview
@Composable
fun PreviewPostRecruitingScreen() {
    PostRecruitingScreen(rememberNavController())
}