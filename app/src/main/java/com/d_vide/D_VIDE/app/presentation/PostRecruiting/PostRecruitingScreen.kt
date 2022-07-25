package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.app.TimePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.component.BottomButton
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.background
import java.util.*

@Composable
fun PostRecruitingScreen(
    navController: NavController,
    viewModel: PostRecruitingViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { TopRoundBar("D/VIDE 모집글 작성") },
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .background(background)
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            EditableFieldItem(labelText = "제목") { EditableTextField() {} }
            EditableFieldItem(labelText = "가게이름") { EditableTextField() {} }
            EditableFieldItem(labelText = "카테고리") { DropDownComp() }
            EditableFieldItem(labelText = "배달비") { EditableTextField(unitText = "원") {} }
            EditableFieldItem(labelText = "주문자 수") { EditableTextField(unitText = "명") {} }
            EditableFieldItem(labelText = "마감시간") { timePicker() }
            EditableFieldItem(labelText = "사진", height = 100.dp) { photoPicker() }
            EditableFieldItem(labelText = "장소", height = 200.dp) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 23.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp),
                            clip = true
                        )
                        .border(1.dp, Color.Gray, RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp))
                        .background(color = Color(0xFFFFFFFF)),
                    contentAlignment = Alignment.Center
                ) {}
            }
            EditableFieldItem(labelText = "내용", height = 200.dp) {
                EditableTextField(height = 200.dp, singleLine = false) {}
            }
            Spacer(modifier = Modifier.padding(10.dp))
            BottomButton("업로드 하기", onClick = {
                navController.navigate(Screen.RecruitingsScreen.route)
            })
        }
        it
    }
}

@Composable
fun DropDownComp() {
    val categoryList = listOf("분식", "한식", "일식", "중식", "디저트", "아시안")
    var selectedText by remember { mutableStateOf("") }
    EditableTextField(
        inputText = selectedText,
        readOnly = true,
        contentAlignment = Alignment.CenterEnd
    ) {
        var isDropDownMenuExpanded by remember { mutableStateOf(false) }
        Button(
            onClick = {
                isDropDownMenuExpanded = true
            },
            modifier = Modifier
                .size(45.dp)
                .padding(end = 3.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFF8F8F8F),
                backgroundColor = Color(0xFFFFFFFF)
            )
        ) {
            Icon(
                Icons.Default.ArrowDropDownCircle,
                contentDescription = "content description",
                modifier = Modifier.size(50.dp)
            )
        }

        DropdownMenu(
            modifier = Modifier.wrapContentSize(),
            expanded = isDropDownMenuExpanded,
            onDismissRequest = { isDropDownMenuExpanded = false },
        ) {
            repeat(6) {
                DropdownMenuItem(
                    modifier = Modifier.width(200.dp),
                    onClick = {
                        selectedText = categoryList.get(it)
                        isDropDownMenuExpanded = false
                    },
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    Text(text = categoryList.get(it))
                }
            }
        }
    }
}

@Composable
fun timePicker() {
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour 시 $mMinute 분"
        }, mHour, mMinute, false
    )
    Box(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp),
                clip = true
            )
            .clickable(onClick = { mTimePickerDialog.show() }),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = mTime.value,
            onValueChange = { mTime.value = it },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(60.dp)
                .align(Alignment.CenterEnd)
                .background(color = Color(0xFFFFFFFF))
                .padding(0.dp),
            enabled = false,
            shape = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp),
        )
    }
}

@Composable
fun photoPicker(
    viewModel: PostRecruitingViewModel = hiltViewModel()
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> viewModel.imageUri.value = uri }
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
                        if (isSelected) viewModel.imageUri.value
                        else R.drawable.add_photo
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(R.drawable.add_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 75.dp)
                    .clip(shape = RoundedCornerShape(15.dp))

            )
        }
        Spacer(modifier = Modifier.padding(start = 10.dp))
    }
}

@Preview
@Composable
fun PreviewPostRecruitingScreen() {
    val navController = rememberNavController()
    PostRecruitingScreen(navController)
}