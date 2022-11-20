package com.d_vide.D_VIDE.app.presentation.view.RecruitingDetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.view.component.PhotoPicker
import com.d_vide.D_VIDE.app.presentation.view.component.DivideButton
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.NumberFormatting
import com.d_vide.D_VIDE.app.presentation.util.addFocusCleaner
import com.d_vide.D_VIDE.app.presentation.view.RecruitingDetail.component.OrderFormField
import com.d_vide.D_VIDE.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecruitingOrderForm(
    onChattingSelected: (Long) -> Unit,
    navController: NavController,
    postId: Long = 0,
    viewModel: PostRecruitingOrderViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
){
    val scrollableState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is PostRecruitingOrderViewModel.UiEvent.ShowSnackbar -> {
                    Log.d("test", "뭔가 문제")
                    navController.navigateUp()
                }
                is PostRecruitingOrderViewModel.UiEvent.SaveRecruiting -> {
                    navController.navigateUp()
                    navController.navigate("${Screen.ChattingDetailScreen.route}/$postId")
                    Log.d("test", "성공 & 뒤로가자")
                }
            }
        }
    }
    Column(
        modifier = modifier
            .padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)
            .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
            .background(White)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .verticalScroll(scrollableState)
            .addFocusCleaner(LocalFocusManager.current)
    ){
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ){
            Divider(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 28.dp)
                    .size(35.5.dp, 3.dp)
                    .align(CenterHorizontally)
                    .clip(RoundedCornerShape(1.5.dp)),
                color = Color(0xFFE1E1E1)
            )
            Text(
                text = "주문 메뉴",
                style = TextStyles.Big2,
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = "삼첩분식 (한남점)",
                style = TextStyles.Point4,
                color = main1
            )
            Spacer(modifier = Modifier.height(25.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                viewModel.imageUris.forEachIndexed { idx, it ->
                    item {
                        PhotoPicker(
                            R.drawable.add_big_photo, it,
                            { viewModel.onEvent(PostRecruitingOrderEvent.EnteredImage(it, idx)) },
                            { viewModel.onEvent(PostRecruitingOrderEvent.DeleteImage(idx)) },
                            modifier = Modifier.height(263.dp)
                        )
                    }
                }
                if (viewModel.imageUris.size < 3) item {
                    PhotoPicker(
                        iconId = R.drawable.add_big_photo,
                        onGetContent = {
                            viewModel.onEvent(
                                PostRecruitingOrderEvent.EnteredImage(it, -1)
                            )
                        },
                        modifier = Modifier.height(263.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row {
                Text(
                    text = "총 주문 금액",
                    style = TextStyles.Big2,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(end = 34.dp)
                )
                OrderFormField(
                    unitText = "원",
                    singleLine = true,
                    inputText = viewModel.recruitingOrderDTO.value.orderPrice?.toString() ?: "",
                    visualTransformation = NumberFormatting(),
                    onValueChange = {
                        if (it.length < 10) {
                            viewModel.onEvent(
                                PostRecruitingOrderEvent.EnteredOrderPrice(
                                    if (it.isNullOrBlank()) null else it.toInt()
                                )
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(59.dp))

        }
        DivideButton(
            onClick = {
                viewModel.onEvent(PostRecruitingOrderEvent.EnteredPostId(postId))
                viewModel.onEvent(PostRecruitingOrderEvent.SaveRecruitingOrder)
                viewModel.onEvent(PostRecruitingOrderEvent.EnterChatting(postId))
                //onChattingSelected(postId)
            },
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(CenterHorizontally)
                .height(50.dp)
                .fillMaxWidth(),
            backgroundColor = if (viewModel.recruitingOrderDTO.value.orderPrice == null) gray4 else main2
        ) {
            Text(
                style = TextStyles.Big1,
                text = if (viewModel.recruitingOrderDTO.value.orderPrice == null) "D/VIDE!"
                       else "D/VIDER와 대화하기",
                color = White,
            )
        }
    }
}
