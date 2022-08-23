package com.d_vide.D_VIDE.app.presentation.MyOrders.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.util.formatAmountOrMessage
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun MyCompletedOrder(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    imageURL: String = "",
    date: String = "2022.08.01",
    title: String = "삼첩분식 드실분~저는 빨리먹고 싶어sdfsdf요.",
    time: String = "14:16",
    price: Int = 27500,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(181.dp)
            .background(background)
            .scale(0.9f)
    ) {
        OrderedDate(date = date)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 22.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 26.dp,
                        topEnd = 26.dp
                    )
                )
                .background(Color.White)
                .clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 19.dp)
                    .padding(top = 17.dp)
            ) {
                OrderImage(imageURL = imageURL)
                Column(
                    modifier = Modifier
                        .padding(start = 18.dp)
                        .padding(end = 31.dp)
                ) {
                    OrderTitle(Modifier, title)
                    OrderTime(
                        time = time,
                        modifier = Modifier
                            .padding(top = 22.dp)
                            .fillMaxWidth()
                            .height(22.dp)
                    )
                    OrderPrice(
                        price = price,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(19.dp)
                    )
                }
            }
        }
        OrderWrite(
            modifier = Modifier
                .clickable(onClick = onButtonClick)
                .background(if (enabled) main2 else gray0)
                .fillMaxWidth()
                .height(38.dp)
                .align(BottomCenter)
        )
    }
}

@Composable
fun OrderImage(
    modifier: Modifier = Modifier,
    imageURL: String = ""
) {
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
        alignment = Alignment.Center
    )
}
@Composable
fun OrderedDate(
    modifier: Modifier = Modifier,
    date: String = "2022.08.01"
){
    Row(
        modifier = modifier.height(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = "circle",
            modifier = Modifier
                .padding(start = 4.dp, end = 6.dp)
                .size(3.dp),
            tint = gray9
        )
        Text(
            text = date,
            color = gray9,
            style = TextStyles.Basics2
        )
    }
}
@Composable
fun OrderTitle(
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
fun OrderTime(
    modifier: Modifier = Modifier,
    time: String = "13:16"
){
    Box(
        modifier = modifier
    ){
        Text(
            modifier = Modifier
                .padding(vertical = 0.dp)
                .align(Alignment.TopStart),
            text = "주문시간",
            style = TextStyles.Basics1,
            color = main_gray2
        )
        Text(
            modifier = Modifier.align(Alignment.TopEnd),
            text = time,
            style = TextStyles.Basics1,
            color = main_gray2
        )
    }
}
@Composable
fun OrderPrice(
    modifier: Modifier = Modifier,
    price: Int = 27500
){
    Box(
        modifier = modifier
    ){
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "주문금액",
            style = TextStyles.Basics1,
            color = main_gray2
        )
        Row(
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Text(
                text = formatAmountOrMessage(price.toString()),
                style = TextStyles.Basics4
            )
            Text(
                modifier = Modifier.padding(start = 4.dp, top = 1.dp),
                text = "원",
                style = TextStyles.Basics1,
                color = main_gray2

            )
        }
    }
}
@Composable
fun OrderWrite(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Center
    ){
        Image(
            painter = painterResource(id = R.drawable.write_review),
            contentDescription = "write_review",
            modifier = Modifier.size(74.82.dp, 16.dp).align(Center)
        )
    }
}
