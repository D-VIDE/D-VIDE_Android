package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.app.TimePickerDialog
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.addFocusCleaner
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.google.accompanist.flowlayout.FlowRow
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.flow.collectLatest
import java.util.*


val datalist = listOf("분식", "한식", "치킨", "일식", "디저트", "피자", "패스트푸드", "족발", "카레", "....")

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
                    ExpandedCategory(
                        onTagClick = {
                            isDropDownMenuExpanded = !isDropDownMenuExpanded
                            selectedText = it
                        },
                        currentTag = selectedText
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
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredDeliveryPrice(if (it.isNullOrBlank()) 0 else it.toInt())) }
                )
            }

            // 목표 금액
            EditableFieldItem(labelText = "목표 금액") {
                EditableTextField(
                    unitText = "원",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    inputText = viewModel.recruitingBodyDTO.value.targetPrice?.toString() ?: "",
                    onValueChange = { viewModel.onEvent(PostRecruitingsEvent.EnteredTargetPrice(if (it.isNullOrBlank()) 0 else it.toInt())) }
                )
            }

            // 마감시간
            EditableFieldItem(labelText = "마감시간") { timePicker() }

            // 사진
            EditableFieldItem(labelText = "사진", height = 80.dp) {
                LazyRow() {
                    item { photoPicker(iconId = R.drawable.add_photo) }
                    item { photoPicker(iconId = R.drawable.add_photo) }
                }
            }

            // 장소
            EditableFieldItem(labelText = "장소", height = 200.dp) {
                locationSelector(viewModel.cameraPositionState.value)
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

@Composable
fun locationSelector(
    cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition(LatLng(35.232234, 129.085211), 17f, 1.0f, 0f)
    }
) {
    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 0.dp),
                clip = true
            ),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
            title = "PathFinder",
            snippet = "Marker in PathFinder"
        )
    }
}


@Composable
fun DropDownComp(
    isDropDownMenuExpanded: Boolean,
    onCheckedChange: () -> Unit,
    selectedText: String,
) {
    EditableTextField(
        inputText = selectedText,
        readOnly = true,
        contentAlignment = Alignment.CenterEnd,
    ) {
        IconToggleButton(
            modifier = Modifier.size(36.dp),
            checked = isDropDownMenuExpanded,
            onCheckedChange = { onCheckedChange() },
        ) {
            Image(
                painterResource(id = R.drawable.dropdown_button),
                contentDescription = "content description",
                modifier = Modifier.size(36.dp),
            )
        }

    }
}

/**
 * click된 category state필요
 */

@Composable
fun ExpandedCategory(
    modifier: Modifier = Modifier.fillMaxWidth(),
    currentTag: String,
    onTagClick: (String) -> Unit,
) {
    Row(Modifier.padding(bottom = 12.dp)) {
        Spacer(
            modifier = Modifier
                .padding(start = 99.dp)
                .height(80.dp)
        )
        Surface(
            color = Color(0xFFEFEFF0),
            modifier = Modifier
                .fillMaxWidth(1f)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 18.dp),
                    clip = true
                )
        ) {
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 50.dp)
                    .padding(horizontal = 10.dp)
            ) {
                datalist.forEach {
                    if (currentTag == it) ItemTag(it, true, onTagClick)
                    else ItemTag(it, false, onTagClick)
                }
            }
        }
    }
}

@Composable
fun timePicker() {
    val mContext = LocalContext.current
    val mHour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
    val mMinute = Calendar.getInstance()[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour 시 $mMinute 분"
        }, mHour, mMinute, false
    )

    EditableTextField(
        modifier = Modifier.clickable(onClick = { mTimePickerDialog.show() }),
        inputText = mTime.value,
        readOnly = true,
        enabled = false
    ) {}
}

@Composable
fun photoPicker(
    @DrawableRes iconId: Int,
//    viewModel: PostRecruitingViewModel
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }
    var isSelected by remember { mutableStateOf(false) }

    Row() {
        Button(
            onClick = {
                isSelected = true
                launcher.launch("image/*")
            },
            modifier = Modifier
                .width(80.dp)
                .height(75.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0x00000000)
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        if (isSelected) imageUri
                        else R.drawable.add_photo
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(iconId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 75.dp)
                    .clip(shape = RoundedCornerShape(15.dp))

            )
        }
        Spacer(modifier = Modifier.padding(start = 10.dp))
    }
}


@Composable
private fun ItemTag(
    string: String = "기본",
    isClicked: Boolean = false,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isClicked) mainOrange else Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .clickable { onClick(string) },
    ) {
        Text(
            text = "$string",
            color = if (isClicked) Color.White
            else Color(0xFF8D8D8D),
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
@Preview
fun PreviewItemTag() {
    Box {
        if (true) {
            ExpandedCategory(
                onTagClick = {},
                currentTag = "selected"
            )
        }
        EditableFieldItem(labelText = "카테고리") {
            DropDownComp(
                isDropDownMenuExpanded = true,
                onCheckedChange = {},
                selectedText = "selected"
            )
        }
    }
}


@Preview
@Composable
fun PreviewPostRecruitingScreen() {
    PostRecruitingScreen(rememberNavController())
}