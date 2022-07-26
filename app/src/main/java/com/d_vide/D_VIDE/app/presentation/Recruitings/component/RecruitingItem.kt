package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.*
import java.text.DecimalFormat

@Composable
fun RecruitingItem(
    modifier: Modifier = Modifier,
    userName: String = "kksmedd10204",
    userLocation: String = "세종시 조치원읍",
    timeRemaining: Int = 36,
    title: String = "삼첩분식 드실분~저는 빨리먹고 싶어요.",
    deadLineHour: Int = 4,
    deadLineMinute: Int = 0,
    insufficientMoney: Int = 0,
    progress: Float = 0.5f
) {
    Box(
        modifier = modifier
            .fillMaxWidth().height(156.dp)
            .scale(0.9f)
            .background(background)
    ) {
        RecruitingUserName(Modifier, userName, userLocation)
        MessageBallon(
            modifier = Modifier
                .align(TopEnd)
                .padding(end = 11.dp)
                .size(98.dp, 30.dp),
            timeRemaining
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                .clickable {
                           // TODO
                },
            shape = RoundedCornerShape(
                topStart = 26.dp,
                topEnd = 26.dp
            ),
            backgroundColor = White
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 19.dp)
                    .padding(top = 17.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.food),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "food",
                    modifier = Modifier
                        .size(64.dp, 86.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    alignment = Center
                )
                Column(
                    modifier = Modifier.padding(start = 18.dp)
                ) {
                    RecruitingTitle(Modifier, title)
                    Row (
                        modifier = Modifier.padding(top = 18.dp)
                    ){
                        RecruitingDeadLine(Modifier, deadLineHour, deadLineMinute)
                        Divider(
                            color = line_gray,
                            modifier = Modifier
                                .padding(start = 22.dp)
                                .padding(end = 36.dp)
                                .height(55.dp)
                                .width(1.dp)
                        )
                        RecruitingInsufficientMoney(Modifier, insufficientMoney)
                    }
                }
            }
            Row{
                LinearProgressIndicator(
                    progress = progress,
                    color = mainYellow,
                    backgroundColor = graph_gray,
                    modifier = Modifier.align(Bottom)
                        .fillMaxWidth()
                        .height(7.dp)
                )
            }
        }
    }
}
@Composable
fun RecruitingUserName(
    modifier: Modifier = Modifier,
    userName: String = "kksmedd10204",
    userLocation: String = "세종시 조치원읍"
){
    Row(
        modifier = Modifier
            .padding(start = 19.dp)
            .padding(bottom = 7.dp)
            .background(background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.character_circle),
            contentDescription = "character",
            modifier = Modifier
                .clip(CircleShape)
                .background(White)
                .size(28.dp),
            alignment = Alignment.Center
        )
        Text(
            text = userName,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(start = 8.dp),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = userLocation,
            fontSize = 10.sp,
            color = recruit_city,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}
@Composable
fun MessageBallon(
    modifier: Modifier = Modifier,
    timeRemaining: Int = 36
){
    Box(
        modifier = modifier
    ){
        Image(
            painterResource(id = R.drawable.message_ballon),
            contentDescription = "message_ballon",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = timeRemaining.toString() + "분 후 주문 예정",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier
                .size(98.dp, 23.dp)
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
        text = title,
        maxLines = 1,
        fontSize = 16.sp,
        modifier = modifier.width(200.dp),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RecruitingDeadLine(
    modifier: Modifier = Modifier,
    deadLineHour: Int = 4,
    deadLineMinute: Int = 0
) {
    Box(
        modifier = modifier
    ){
        Column{
            Text(
                text = "마감시간",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = text_gray,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(start = 13.dp)
            )
            Row{
                Text(
                    text = "오후",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainYellow,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text =
                        if (deadLineHour < 9 && deadLineMinute < 9)
                            "0" + deadLineHour.toString() + ":0" + deadLineMinute.toString()
                        else if (deadLineHour < 9 && deadLineMinute > 9)
                            "0" + deadLineHour.toString() + ":" + deadLineMinute.toString()
                        else if (deadLineHour > 9 && deadLineMinute < 9)
                            deadLineHour.toString() + ":0" + deadLineMinute.toString()
                        else
                            deadLineHour.toString() + ":" + deadLineMinute.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = mainOrange,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }

}
@Composable
fun RecruitingInsufficientMoney(
    modifier: Modifier = Modifier,
    insufficientMoney: Int = 0
) {
    val pattern = DecimalFormat("#,###")
    Row{
        Column(
            modifier = modifier.align(CenterVertically)
        ){
            Text(
                text = "부족한 금액",
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = text_gray,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
            Text(

                text = pattern.format(insufficientMoney),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = mainOrange,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        Text(
            text = "원",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = mainYellow,
            modifier = Modifier
                .padding(start = 5.dp)
                .padding(top = 25.dp)
        )
    }

}
@Preview(showBackground = true)
@Composable
fun RecruitingUserPreview() {
    DVIDETheme {
        RecruitingItem()
    }
}