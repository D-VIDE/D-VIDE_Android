package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.SemicircleEditableTextField
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun PostRecruitingScreen(
    navController: NavController,
//    viewModel: PostRecruitingViewModel = hiltViewModel(),
) {
    Scaffold(
        floatingActionButton = {
            FloatingButton(
                text = "업로드하기",
                onClick = { /*TODO*/ },
                fontSize = 20.sp
            )
       },
        floatingActionButtonPosition = FabPosition.Center,
    ) {}

    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Spacer(modifier = Modifier.padding(50.dp))

        SemicircleEditableTextField(labelText = "제목")
        SemicircleEditableTextField(labelText = "가게이름")
        SemicircleEditableTextField(labelText = "배달비")
        SemicircleEditableTextField(labelText = "주문자 수")
        SemicircleEditableTextField(labelText = "마감시간")
        SemicircleEditableTextField(labelText = "장소",height = 200.dp)
        SemicircleEditableTextField(
            labelText = "내용",
            singleLine = false,
            height = 200.dp
        )

        Spacer(modifier = Modifier.padding(50.dp))
    }

}

@Preview
@Composable
fun PreviewPostRecruitingScreen() {
    val navController = rememberNavController()
    PostRecruitingScreen(navController)
}