package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.component.BottomButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundTextContainer
import com.d_vide.D_VIDE.app.presentation.navigation.Screen

@Composable
fun PostRecruitingScreen(
    navController: NavController,
//    viewModel: PostRecruitingViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    Surface() {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            TopRoundTextContainer(text = "D/VIDE 모집글 작성")
            Spacer(modifier = Modifier.padding(16.dp))

            EditableFieldItem(labelText = "제목") { EditableTextField() {} }
            EditableFieldItem(labelText = "가게이름") { EditableTextField() {} }
            EditableFieldItem(labelText = "카테고리") {
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
                        modifier= Modifier.size(44.dp),
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
                        val categoryList = listOf("분식", "한식", "일식", "중식", "디저트", "아시안")
                        repeat(6) {
                            DropdownMenuItem(
                                onClick = {
                                    selectedText = categoryList.get(it)
                                    isDropDownMenuExpanded = false
                                }
                            ) { Text(text = categoryList.get(it)) }
                        }
                    }
                }
            }
            EditableFieldItem(labelText = "배달비") { EditableTextField(unitText = "원") {} }
            EditableFieldItem(labelText = "주문자 수", ) { EditableTextField(unitText = "명") {} }
            EditableFieldItem(labelText = "마감시간") { EditableTextField(unitText = "분") {} }
            EditableFieldItem(labelText = "사진") {}
            EditableFieldItem(labelText = "장소", height = 200.dp) {}
            EditableFieldItem(labelText = "내용", height = 200.dp) {
                EditableTextField(height = 200.dp, singleLine = false) {}
            }

            Spacer(modifier = Modifier.padding(10.dp))
            BottomButton("업로드 하기", onClick = {
                navController.navigate(Screen.RecruitingsScreen.route)
            })
        }
    }
}

@Preview
@Composable
fun PreviewPostRecruitingScreen() {
    val navController = rememberNavController()
    PostRecruitingScreen(navController)
}