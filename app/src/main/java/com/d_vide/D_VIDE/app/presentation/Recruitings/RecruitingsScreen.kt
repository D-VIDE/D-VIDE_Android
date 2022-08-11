package com.d_vide.D_VIDE.app.presentation.Recruitings

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingCategory
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToHour
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToMinute
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.background
import kotlinx.coroutines.launch
import java.sql.Timestamp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecruitingsScreen(
    navController: NavController,
    viewModel: RecruitingsViewModel = hiltViewModel()
) {
    BottomSheetUserFeedSreen(
        navController = navController
    ) { state, scope ->
        Scaffold(
            floatingActionButton = {
                FloatingButton(
                    text = "지금 D/VIDE 하기",
                    onClick = { navController.navigate(Screen.PostRecruitingScreen.route) },
                    shouldShowBottomBar = true
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(background)
            ) {
                categoryContainer()
                LazyColumn(
                    modifier = Modifier.align(CenterHorizontally),
                    horizontalAlignment = CenterHorizontally,
                    contentPadding = PaddingValues(top = 13.dp, bottom = 150.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.width(9.dp))
                    }
                    viewModel.state.value.recruitingDTOS.forEach {
                        item {
                            RecruitingItem(
                                onClick = {
                                    scope.launch {
                                        state.animateTo(
                                            ModalBottomSheetValue.Expanded,
                                            tween(500)
                                        )
                                    }
                                },
                                userName = it.nickname,
                                userLocation = "${it.latitude}, ${it.longitude}",
                                title = it.title,
                                imageURL = it.profileImgUrl,
                                insufficientMoney = it.targetPrice,
                                timeRemaining = ((it.targetTime - System.currentTimeMillis()/1000) / 60).toInt(),
                                deadLineHour = it.targetTime.convertTimestampToHour(),
                                deadLineMinute = it.targetTime.convertTimestampToMinute()
                            )
                        }
                    }
                    item {
                        BlankIndicator(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(vertical = 78.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun categoryContainer() {
    var selectedItem by remember { mutableStateOf("") }
    val categoryList = listOf("분식", "한식", "일식", "중식", "양식", "디저트", "피자", "패스트푸드")

    TopRoundContainer {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            item {
                Spacer(modifier = Modifier.width(11.dp))
            }
            this.items(items = categoryList) {
                Row {
                    RecruitingCategory(
                        text = it,
                        isSelected = selectedItem == it,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .selectable(
                                selected = selectedItem == it,
                                onClick = { selectedItem = it }
                            )
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DVIDETheme {
        RecruitingsScreen(rememberNavController())
    }
}