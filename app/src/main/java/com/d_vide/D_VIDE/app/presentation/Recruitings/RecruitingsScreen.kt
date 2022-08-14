package com.d_vide.D_VIDE.app.presentation.Recruitings

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingCategory
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.RecruitingWriteButton
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.LocationConverter
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToHour
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToMinute
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.background
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecruitingsScreen(
    navController: NavController,
    viewModel: RecruitingsViewModel = hiltViewModel(),
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit
) {
    var selectedCategory: Category = Category.ALL
    var containerCategory = ""

    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick
    ) { state, scope ->
        Scaffold(
            floatingActionButton = {
                RecruitingWriteButton(
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
                containerCategory = categoryContainer(
                    onCategoryChange = {
                        viewModel.getRecruitings(category = it)
                    }
                )
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
                         //   if (it.category == containerCategory || containerCategory == "") {
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
                                    userLocation = LocationConverter(
                                        LatLng(
                                            it.latitude,
                                            it.longitude
                                        )
                                    ),
                                    title = it.title,
                                    imageURL = it.profileImgUrl,
                                    insufficientMoney = it.targetPrice,
                                    timeRemaining = ((it.targetTime - System.currentTimeMillis() / 1000) / 60),
                                    deadLineHour = it.targetTime.convertTimestampToHour(),
                                    deadLineMinute = it.targetTime.convertTimestampToMinute()
                                )
                     //       }
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
fun categoryContainer(
    recruitingCategory: Category = Category.ALL,
    onCategoryChange: (Category) -> Unit
) : String{
    var selectedItem by remember { mutableStateOf("") }

    TopRoundContainer {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            item {
                Spacer(modifier = Modifier.width(11.dp))
            }
            item {
                Category::class.sealedSubclasses.mapNotNull { it.objectInstance }.forEach {
                    Row {
                        RecruitingCategory(
                            text = it.name,
                            isSelected = selectedItem == it.tag,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .selectable(
                                    selected = selectedItem == it.tag,
                                    onClick = {
                                        selectedItem = it.tag
                                        onCategoryChange(it)
                                    },
                                )
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                    }
                }
            }
        }
    }
    return selectedItem
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DVIDETheme {
        //RecruitingsScreen(rememberNavController())
    }
}