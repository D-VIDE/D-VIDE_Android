package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.util.convertMinuteToHour
import com.d_vide.D_VIDE.ui.theme.*
import java.text.DecimalFormat

@Composable
fun RecruitingItem(
    modifier: Modifier = Modifier,
    onUserClick: () -> Unit = {},
    onClick: () -> Unit,
    userName: String = "kksmedd104",
    userLocation: String = "세종시 조치원읍",
    timeRemaining: Long = 2,
    imageURL: String = "",
    title: String = "삼첩분식 드실분~저는 빨리먹고 싶어sdfsdf요.",
    deadLineHour: Int = 4,
    deadLineMinute: Int = 0,
    insufficientMoney: Int = 15000,
    progress: Float = 0.5f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(156.dp)
            .background(background)
            .scale(0.9f)
    ) {
        RecruitingUserName(Modifier, userName, userLocation, imageURL, onUserClick)
        if(timeRemaining > 0) {
            MessageBallon(
                modifier = Modifier
                    .align(TopEnd)
                    .padding(end = 11.dp),
                timeRemaining
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 35.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 26.dp,
                        topEnd = 26.dp
                    )
                )
                .background(White)
                .clickable(onClick = onClick)
            ,
            backgroundColor = White
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 19.dp)
                    .padding(top = 17.dp)
            ) {
                RecruitingImage(imageURL = imageURL)
                Column(
                    modifier = Modifier.padding(start = 18.dp)
                ) {
                    RecruitingTitle(Modifier.padding(end = 27.dp), title)
                    Row (
                        modifier = Modifier.padding(bottom = 5.dp, top = 18.dp, end = 27.dp)
                    ){
                        Column(
                            modifier = Modifier.weight(0.5f)
                        ) {
                            RecruitingDeadLine(Modifier.align(Start), deadLineHour, deadLineMinute)
                        }
                        Divider(
                            color = line_gray,
                            modifier = Modifier
                                .height(55.dp)
                                .width(1.dp)
                                .align(CenterVertically)
                        )
                        Column(
                            modifier = Modifier.weight(0.5f)
                        ) {
                           RecruitingInsufficientMoney(Modifier.align(End), insufficientMoney)
                        }
                    }
                }
            }
            LinearProgressIndicator(
                progress = progress,
                color = if (progress == 1f) main1 else main0,
                backgroundColor = graph_gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(BottomCenter)
                    .height(7.dp)
            )

        }
    }
}

@Composable
fun RecruitingImage(
    modifier: Modifier = Modifier,
    imageURL: String = ""
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.food),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp, 86.dp)
            .clip(RoundedCornerShape(12.dp)),
        alignment = Center

    )
}

@Composable
fun RecruitingUserName(
    modifier: Modifier = Modifier,
    userName: String = "kksmedd10204",
    userLocation: String = "세종시 조치원읍",
    imageURL: String = "",
    onClick: () -> Unit={}
){
    Row(
        modifier = Modifier
            .padding(start = 19.dp)
            .padding(bottom = 7.dp)
            .background(background)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
      //  Row(
      //      modifier = Modifier.clickable(onClick = onClick),
      //      verticalAlignment = Alignment.CenterVertically
      //  ) {
            DivideImage(
                modifier = Modifier
                    .size(28.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    ),
                imageURL = imageURL
            )
            Text(
                text = userName,
                style = TextStyles.Basics1,
                color = Black,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Text(
                text = userLocation,
                style = TextStyles.Small1,
                color = recruit_city,
                modifier = Modifier.padding(start = 10.dp)
            )
       // }
    }
}
@Composable
fun MessageBallon(
    modifier: Modifier = Modifier,
    timeRemaining: Long = 36
){
    Box(
        modifier = modifier
            .padding(top = 5.dp)
            .size(98.dp, 30.dp)
    ){
        Image(
            painterResource(id = R.drawable.message_ballon),
            contentDescription = "message_ballon",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = convertMinuteToHour(timeRemaining) + " 후 주문 예정",
            color = White,
            style = TextStyles.Small3,
            modifier = Modifier
                .size(98.dp, 21.dp)
                .align(Center),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun RecruitingTitle(
    modifier: Modifier = Modifier,
    title: String = "삼첩분식 드실분~저는 빨리먹고 싶어요."
){
    Text(
        modifier = modifier,
        text = title,
        maxLines = 1,
        style = TextStyles.Point1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RecruitingDeadLine(
    modifier: Modifier = Modifier,
    deadLineHour: Int = 0,
    deadLineMinute: Int = 0
) {
    val hours = if(deadLineHour > 12) deadLineHour-12 else deadLineMinute
    val hoursStr = if(hours < 10) "0$hours" else "$hours"
    val minutesStr = if(deadLineMinute < 10) "0$deadLineMinute" else "$deadLineMinute"

    Row(
        modifier = modifier
    ){
        Column{
            Text(
                text = "마감시간",
                style = TextStyles.Small1,
                textAlign = TextAlign.Center,
                color = text_gray,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 13.dp)
            )
            Row{
                Text(
                    text = if(deadLineHour > 12) "오후" else "오전",
                    style = TextStyles.Point3,
                    color = main0,
                    modifier = Modifier.padding(top = 7.dp)
                )
                Text(
                    text = "$hoursStr:$minutesStr",
                    style = TextStyles.Big1,
                    color = main1,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }

}
@Composable
fun RecruitingInsufficientMoney(
    modifier: Modifier = Modifier,
    insufficientMoney: Int = 15000
) {
    val pattern = DecimalFormat("#,###")
    Row(
        modifier = modifier
    ){
      //  Spacer(modifier = Modifier.width(16.dp))
        Column{
            Text(
                text = "부족한 금액",
                style = TextStyles.Small1,
                textAlign = TextAlign.Center,
                color = text_gray,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
            Text(
                text = pattern.format(insufficientMoney),
                style = TextStyles.Big1,
                color = main1,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        Text(
            text = "원",
            style = TextStyles.Point3,
            color = main0,
            modifier = Modifier
                .padding(start = 5.dp)
                .padding(top = 20.dp)
        )
    }

}
