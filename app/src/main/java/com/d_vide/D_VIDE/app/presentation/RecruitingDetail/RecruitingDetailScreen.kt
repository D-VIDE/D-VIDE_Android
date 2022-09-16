package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.presentation.component.DivideButton
import com.d_vide.D_VIDE.app.presentation.component.DivideDivider
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.component.TopRectangleBar
import com.d_vide.D_VIDE.app.presentation.util.formatAmountOrMessage
import com.d_vide.D_VIDE.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecruitingDetailScreen(
    postId: Int = 0,
    navController: NavController,
    upPress: () -> Unit = {}
){
    val scrollableState = rememberScrollState()
    val viewModel  = hiltViewModel<RecruitingDetailViewModel>()
    val recruitingDetail = viewModel.recruitingDetail.value.recruitingDetail
    val postDetail = recruitingDetail.postDetail
    val userDetail = recruitingDetail.user
    val postId = viewModel.postId!!

    BottomSheetOrderForm(
        navController = navController,
        postId = postId
    ) { state, scope ->
        Box(
            modifier = Modifier
                .padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)
                .fillMaxSize()
                .verticalScroll(scrollableState)
        ) {
            Column{
                    //게시자 정보를 나타내는 topAppBar
                    TopRectangleBar(
                        title = userDetail.nickname,
                        imageURL = userDetail.profileImgUrl,
                        upPress = upPress
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    //모집 글 사진
                    RecrutingPager(postDetail.postImgUrls)
                    Spacer(modifier = Modifier.size(34.dp))

                    Column(modifier = Modifier.padding(horizontal = 36.dp)) {
                        //모집 글 제목
                        Text(text = postDetail.title, style = TextStyles.Big1, color = gray5)
                        DivideDivider(
                            Modifier
                                .alpha(0.2f)
                                .padding(vertical = 14.dp)
                        )
                        //마감시간
                        //subtext: 시간
                        //unit: AM, PM
                        RecruitingSmallText(text = "마감시간", subtext = "04:00", unit = "PM")
                        Spacer(modifier = Modifier.size(12.dp))
                        //배달비
                        //subtext: 배달비
                        RecruitingSmallText(text = "배달비", subtext = formatAmountOrMessage(postDetail.deliveryPrice.toString()), unit = "원")
                        DivideDivider(
                            Modifier
                                .alpha(0.2f)
                                .padding(vertical = 14.dp)
                        )
                        //목표 주문 금액
                        //subtext = 목표 주문 금액
                        RecruitingBigText(text = "목표 주문 금액", subtext = formatAmountOrMessage(postDetail.targetPrice.toString()), unit = "원")
                        Spacer(modifier = Modifier.size(12.dp))
                        //현재 주문 금액
                        //subtext = 현재 주문 금액
                        RecruitingBigText(text = "현재 주문 금액", subtext = formatAmountOrMessage(postDetail.orderedPrice.toString()), unit = "원")

                        LinearProgressIndicator(
                            progress = postDetail.targetPrice.toFloat().div(postDetail.orderedPrice.toFloat()),
                            color = main0,
                            backgroundColor = gray4,
                            modifier = Modifier
                                .padding(vertical = 19.dp)
                                .fillMaxWidth()
                                .height(16.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(text = "• 장소", style = TextStyles.Point4, color = gray3)
                            /**
                             * 구체적인 장소가 안나옴..수정필요
                             */
                            Text(
                                text = "역삼디오빌 앞",
                                style = TextStyles.Point4,
                                color = gray3,
                                textAlign = TextAlign.End
                            )
                        }
                        Spacer(modifier = Modifier.height(180.dp))
                }
            }
            DivideButton(
                onClick = {
                    scope.launch {
                        state.animateTo(
                            ModalBottomSheetValue.Expanded,
                            tween(500)
                        )
                    }
                },
                modifier = Modifier
                    .padding(bottom = 30.dp, start = 20.dp, end = 20.dp)
                    .height(50.dp)
                    .align(BottomCenter)
                    .fillMaxWidth()
            ) {
                Text(text = "지금 D/VIDE하기", style = TextStyles.Big1, color = gray7)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetOrderForm(
    navController: NavController,
    postId: Long = 0,
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
){
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetState = state,
        sheetContent = {
            RecruitingOrderForm(
                navController = navController,
                postId = postId,
                modifier = Modifier.fillMaxHeight(0.7f),
            )
        }
    ) {
        activityContentScope(state, scope)
    }
}

/**
 * text :
 */
@Composable
fun RecruitingSmallText(
    modifier: Modifier = Modifier,
    text: String,
    subtext: String,
    unit: String?
){
    Box{
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = CenterStart
        ){
            Text(
                text = "• ${text}",
                style = TextStyles.Point4,
                color = gray3
            )
        }
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = CenterEnd
        ){
            Text(
                text = subtext,
                style = TextStyles.Point4,
                color = gray3,
                modifier = Modifier.padding(end = 23.dp)
            )
            Text(
                text = unit!!,
                style = TextStyles.Basics2,
                color = gray1,
            )

        }
    }
}

@Composable
fun RecruitingBigText(
    modifier: Modifier = Modifier,
    text: String,
    subtext: String,
    unit: String
){
    Box{
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = CenterStart
        ) {
            Text(
                text = "• ${text}",
                style = TextStyles.Point4,
                color = gray3
            )
        }
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = CenterEnd
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = subtext,
                    style = TextStyles.Big1,
                    color = main1
                )
                Text(
                    text = unit,
                    style = TextStyles.Basics2,
                    color = gray1,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun RecrutingPager(
    imageUrls: List<String>
){
    val pagerState = rememberPagerState()

    Box() {

        HorizontalPager(
            count = imageUrls.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 46.dp),
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) { page ->
            DivideImage(
                modifier = Modifier
                    .height(213.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(26.dp)),
                imageURL = imageUrls[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            spacing = 4.dp,
            indicatorHeight = 6.dp,
            indicatorWidth = 6.dp,
            indicatorShape = CircleShape,
            activeColor = gray7,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        )
    }
}