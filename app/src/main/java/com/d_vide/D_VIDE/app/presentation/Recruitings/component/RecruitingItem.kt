package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun RecruitingItem() {
    Column(
        modifier = Modifier
            .width(350.dp)
            .height(161.dp)
            .background(background)
    ) {
        Row (
            modifier = Modifier
                .padding(start = 19.dp)
                .padding(top = 6.dp)
                .fillMaxWidth()
                .background(background)
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
                text = "kksmedd10204",
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 7.6.dp)
                    .padding(top = 5.dp)
                    .height(21.dp)
                    .width(100.dp)
            )
            Text(
                text = "세종시 조치원읍",
                fontSize = 10.sp,
                color = recruit_city,
                modifier = Modifier
                    .padding(start = 1.6.dp)
                    .padding(top = 6.dp)
                    .height(21.dp)
                    .width(100.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth()
                .clickable {
                    // TODO
                }
        ) {
            Canvas(modifier = Modifier
                .height(121.dp)
                .width(350.dp)
            ) {

                val cornerRadius = CornerRadius(26.dp.toPx(), 26.dp.toPx())
                val path = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, 0f),
                                size = size,
                            ),
                            topRight = cornerRadius,
                            bottomRight = cornerRadius,
                        )
                    )
                }
                drawPath(path, color = White)
                drawRect(
                    color = graph_gray,
                    topLeft = Offset(0f, 0f),
                    size = Size(10.dp.toPx(), 33f.dp.toPx())
                )
                drawRect(
                    color = mainYellow,
                    topLeft = Offset(0f, 33.dp.toPx()),
                    size = Size(10.dp.toPx(), 88f.dp.toPx())
                )
            }
            Row (

            ) {
                Image(
                    painterResource(id = R.drawable.food),
                    contentScale = ContentScale.FillBounds,
                     contentDescription = "food",
                     modifier = Modifier
                         .padding(start = 19.dp)
                         .padding(top = 17.dp)
                         .size(64.dp, 86.dp)
                         .clip(RoundedCornerShape(12.dp)),
                     alignment = Alignment.Center
                )
                Column() {
                    Text(
                        text = "삼첩분식 드실분~저는 빨리먹고 싶어요.",
                        maxLines = 1,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 18.dp)
                            .padding(start = 18.dp)
                            .height(41.dp)
                            .width(222.dp)
                    )
                    Row (){
                        Column(){
                            Text(
                                text = "마감시간",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                color =text_gray,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .height(21.dp)
                                    .width(100.dp)
                            )
                            Row(){
                                Text(
                                    text = "오후",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = mainYellow,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                        .padding(start = 18.dp)
                                        .height(21.dp)
                                        .width(19.dp)

                                )
                                Text(
                                    text = "04:00",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = mainOrange,
                                    modifier = Modifier
                                        .padding(top = 1.dp)
                                        .padding(start = 4.dp)
                                )

                            }
                        }
                    }

                }
            }
        }


    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DVIDETheme {
        RecruitingItem()
    }
}