package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.app.TimePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
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
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.UIConst
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.component.BottomButton
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.google.accompanist.flowlayout.FlowRow
import java.util.*


val datalist = listOf("분식", "한식", "치킨", "일식", "디저트", "피자", "패스트푸드", "족발", "카레", "....")

@Composable
fun PostRecruitingScreen(
    navController: NavController,
//    viewModel: PostRecruitingViewModel,
    upPress: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopRoundBar("D/VIDE 모집글 작성", onClick = upPress) },
        floatingActionButton = {
            FloatingButton(
                text = "업로드 하기",
                onClick = { navController.navigate(Screen.RecruitingsScreen.route) },
                shouldShowBottomBar = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .background(background)
                .padding(horizontal = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(bottom = 28.dp))

            // 제목
            EditableFieldItem(labelText = "제목") { EditableTextField() {} }

            // 가게이름
            EditableFieldItem(labelText = "가게이름") { EditableTextField() {} }

            // 카테고리
            Box {
                if(isDropDownMenuExpanded) {
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
                        onCheckedChange = {isDropDownMenuExpanded = !isDropDownMenuExpanded },
                        selectedText = selectedText
                    )
                }
            }

            // 배달비
            EditableFieldItem(labelText = "배달비") { EditableTextField(unitText = "원") {} }

            // 목표 금액
            EditableFieldItem(labelText = "목표 금액") { EditableTextField(unitText = "원") {} }

            // 마감시간
            EditableFieldItem(labelText = "마감시간") { timePicker() }

            // 사진
            EditableFieldItem(labelText = "사진", height = 80.dp) {
                photoPicker(iconId = R.drawable.select_photo)
                photoPicker(iconId = R.drawable.add_photo)
            }

            // 장소
            EditableFieldItem(labelText = "장소", height = 200.dp) {
                /* 구현 예정 */
                EditableTextField(enabled = false, readOnly = true, height = 200.dp) {
                    
                }
            }

            // 내용
            EditableFieldItem(labelText = "내용", height = 200.dp) {
                EditableTextField(height = 200.dp, singleLine = false) {}
            }

            Spacer(modifier = Modifier.padding(50.dp))
        }
        it
    }
}

@Composable
fun DropDownComp(
    isDropDownMenuExpanded: Boolean,
    onCheckedChange: () -> Unit,
    selectedText: String
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
    onTagClick: (String) -> Unit
){
    Row(Modifier.padding(bottom = 12.dp)) {
        Spacer(modifier = Modifier
            .padding(start = 99.dp)
            .height(80.dp))
        Surface(
            color = Color(0xFFEFEFF0),
            modifier = Modifier
                .fillMaxWidth(1f)
                .shadow(
                    elevation = 4.dp,
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
                    if(currentTag == it) ItemTag(it, true, onTagClick)
                    else ItemTag(it,false, onTagClick)
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
    var imageUri  by remember { mutableStateOf<Uri?>(null) }
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
    onClick:(String)->Unit
){
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
fun PreviewItemTag(){
    Box {
        if(true) {
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