package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.LocationSelector
import com.d_vide.D_VIDE.app.presentation.component.DivideButton
import com.d_vide.D_VIDE.app.presentation.component.DivideDivider
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.component.TopRectangleBar
import com.d_vide.D_VIDE.ui.theme.*
import com.google.accompanist.pager.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlin.math.absoluteValue

@Composable
fun RecruitingDetail(
    postId: Int,
    upPress: () -> Unit
){
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            item {
                TopRectangleBar(
                    title = "룡룡",
                    imageURL = "https://item.kakaocdn.net/do/6905d2b5d05b4d0b27ec8731cb8252fa7f9f127ae3ca5dc7f0f6349aebcdb3c4",
                    upPress = upPress
                )
                Spacer(modifier = Modifier.size(15.dp))
                RecrutingPager()
                Spacer(modifier = Modifier.size(34.dp))
                Column(modifier = Modifier.padding(horizontal = 36.dp)) {
                    Text(text = "삼첩분식 드실 분~", style = TextStyles.Big1, color = gray5)
                    DivideDivider(
                        Modifier
                            .alpha(0.2f)
                            .padding(vertical = 14.dp))
                    RecruitingSmallText(text = "마감시간", subtext = "04:00", unit ="PM")
                    Spacer(modifier = Modifier.size(12.dp))
                    RecruitingSmallText(text = "배달비", subtext = "30,000", unit ="원")
                    DivideDivider(
                        Modifier
                            .alpha(0.2f)
                            .padding(vertical = 14.dp))
                    RecruitingBigText(text = "목표 주문 금액", subtext = "30,000", unit ="원")
                    Spacer(modifier = Modifier.size(12.dp))
                    RecruitingBigText(text = "현재 주문 금액", subtext = "16,000", unit ="원")

                    LinearProgressIndicator(
                        progress = 0.7f,
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
                        Text(text = "역삼디오빌 앞", style = TextStyles.Point4, color = gray3, textAlign = TextAlign.End)
                    }

                }
            }

        }

        DivideButton(
            onClick = {},
            modifier = Modifier
                .padding(bottom = 30.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.BottomCenter)
                .height(50.dp)
                .fillMaxWidth()) {
            Text(text = "지금 D/VIDE하기", style = TextStyles.Big1, color = gray7)
        }
    }
}


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
private fun RecrutingPager(){
    val pagerState = rememberPagerState()

    Box() {

        HorizontalPager(
            count = 3,
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
                imageURL = "https://i.ytimg.com/vi/9ONqnsb2adI/maxresdefault.jpg")
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