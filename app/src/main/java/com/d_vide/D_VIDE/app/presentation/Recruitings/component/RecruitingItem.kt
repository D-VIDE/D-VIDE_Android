package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import android.provider.ContactsContract.Profile
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
import androidx.compose.ui.graphics.Color.Companion.Black
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
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ProfileImage
import com.d_vide.D_VIDE.ui.theme.*
import java.text.DecimalFormat

@Composable
fun RecruitingItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    userName: String = "kksmedd104",
    userLocation: String = "세종시 조치원읍",
    timeRemaining: Int = 0,
    imageURL: String = "",
    title: String = "삼첩분식 드실분~저는 빨리먹고 싶어요.",
    deadLineHour: Int = 4,
    deadLineMinute: Int = 0,
    insufficientMoney: Int = 0,
    progress: Float = 0.5f
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(152.dp)
            .background(background)
            .scale(0.9f)
    ) {
        RecruitingUserName(Modifier, userName, userLocation, imageURL, onClick)
        if(timeRemaining > 0) {
            MessageBallon(
                modifier = Modifier
                    .align(TopEnd)
                    .padding(end = 11.dp),
                timeRemaining
            )
        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 35.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 26.dp,
                        topEnd = 26.dp
                    )
                )
                .clickable {
                    // do something
                }
            ,
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
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            RecruitingDeadLine(Modifier.align(Center), deadLineHour, deadLineMinute)
                        }
                        Divider(
                        color = line_gray,
                        modifier = Modifier
                            .height(55.dp)
                            .width(1.dp)
                            .align(CenterVertically)
                        )
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            RecruitingInsufficientMoney(Modifier.align(Center), insufficientMoney)
                        }
                        Spacer(modifier = Modifier.width(27.dp))
                    }
                }
            }
            Row{
                LinearProgressIndicator(
                    progress = progress,
                    color = mainYellow,
                    backgroundColor = graph_gray,
                    modifier = Modifier
                        .align(Bottom)
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
    userLocation: String = "세종시 조치원읍",
    imageURL: String = "",
    onClick: () -> Unit={}
){
    Row(
        modifier = Modifier
            .padding(start = 19.dp)
            .padding(bottom = 7.dp)
            .background(background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(imageURL = imageURL)
        TextButton(
            onClick = onClick,
            modifier = Modifier.height(20.dp),
            contentPadding = PaddingValues(0.dp),
            colors= ButtonDefaults.buttonColors(backgroundColor = background)
        ){
            Text(
                text = userName,
                style = TextStyles.Basics1,
                color = Black,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(CenterVertically)
            )
        }

        Text(
            text = userLocation,
            style = TextStyles.Small1,
            color = recruit_city,
            modifier = Modifier.padding(start = 10.dp)
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
            .padding(top = 5.dp)
            .size(98.dp, 30.dp)
    ){
        Image(
            painterResource(id = R.drawable.message_ballon),
            contentDescription = "message_ballon",
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = timeRemaining.toString() + "분 후 주문 예정",
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
        text = title,
        maxLines = 1,
        style = TextStyles.Point1,
        modifier = modifier.padding(end = 40.dp),
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RecruitingDeadLine(
    modifier: Modifier = Modifier,
    deadLineHour: Int = 4,
    deadLineMinute: Int = 0
) {
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
                    text = "오후",
                    style = TextStyles.Point3,
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
        Spacer(modifier = Modifier.width(16.dp))
    }

}
@Composable
fun RecruitingInsufficientMoney(
    modifier: Modifier = Modifier,
    insufficientMoney: Int = 0
) {
    val pattern = DecimalFormat("#,###")
    Row(
        modifier = modifier
    ){
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
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = mainOrange,
                modifier = Modifier.align(CenterHorizontally)
            )
        }
        Text(
            text = "원",
            style = TextStyles.Point3,
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