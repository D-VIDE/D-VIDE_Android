package com.d_vide.D_VIDE.app.presentation.Recruitings

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app._constants.UIConst
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingCategory
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingItem
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun RecruitingsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { categoryContainer() },
        floatingActionButton = {
            FloatingButton(
                text = "지금 D/VIDE 하기",
                onClick = { navController.navigate(Screen.PostRecruitingScreen.route) },
                shouldShowBottomBar = true
            )
                /*
                Image(
                    painterResource(id = R.drawable.plus_icon),
                    contentDescription = "plus",
                    modifier = Modifier
                        .size(52.dp)
                        .clickable {
                            navController.navigate(Screen.PostRecruitingScreen.route)
                        }
                )
                */
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {
            LazyColumn(
                modifier = Modifier.align(CenterHorizontally),
              //  verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(top = 20.dp, bottom = 170.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                item { RecruitingItem(modifier = Modifier.clickable{
                    navController.navigate(Screen.UserFeedScreen.route)
                })}
                item {
                    RecruitingItem(
                        userName = "asdfasdfsge",
                        userLocation = "부산시 수영구",
                        title = "짬뽕 땡기시는 분~! 빨리 모여라!!",
                        deadLineHour = 6,
                        deadLineMinute = 23,
                        timeRemaining = 9999,
                        insufficientMoney = 15000,
                        progress = 0.3f
                    )
                }
                item {
                    RecruitingItem(
                        userName = "dividividip",
                        userLocation = "부산시 금정구",
                        title = "비건 다이어트 같이 하실 분~",
                        deadLineHour = 8,
                        deadLineMinute = 32,
                        timeRemaining = 66,
                        insufficientMoney = 5000,
                        progress = 1.0f
                    )
                }
                item {
                    RecruitingItem(
                        userName = "hihihi",
                        userLocation = "서울시 강남구",
                        title = "야식으로는 애플파이지!~",
                        deadLineHour = 12,
                        deadLineMinute = 12,
                        timeRemaining = 120,
                        insufficientMoney = 300,
                        progress = 0.7f
                    )
                }
            }
            BlankIndicator(
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.padding(50.dp))
        }
        it
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
                .clip(RoundedCornerShape(26.dp))
        ) {
            item {
                Spacer(modifier = Modifier.width(20.dp))
            }
            this.items(items = categoryList) {
                Row {
                    RecruitingCategory(
                        text = it,
                        isSelected = selectedItem == it,
                        modifier = Modifier
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