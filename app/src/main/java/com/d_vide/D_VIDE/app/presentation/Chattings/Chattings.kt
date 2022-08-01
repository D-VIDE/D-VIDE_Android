package com.d_vide.D_VIDE.app.presentation.Chattings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.mainYellow

//@Composable
//fun Chattings(){
//    LazyColumn(
//    )
//}


@Composable
fun ChattingItemNew(
    modifier: Modifier = Modifier,
    title: String = "삼첩분식 드실 분~ ㅁㄴㅇㄹㅁㄴ",
    imageUrl: String = "https://yt3.ggpht.com/ytc/AKedOLR5NNn9lbbFQqPkHCTMgvgvCjZi94G2JRU7DjsM=s900-c-k-c0x00ffffff-no-rj" ,
    isBorder: Boolean = false,
    titleColor: Color = Color.Black,
    text: String = "넹 좋아요",
){

    Box {
        ChattingItem(
            Modifier.border(2.dp, mainYellow, RoundedCornerShape(25.dp))
        )
        ChatNumber(
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}


@Composable
fun ChattingItem(
    modifier: Modifier = Modifier,
    title: String = "삼첩분식 드실 분~ ㅁㄴㅇㄹㅁㄴ",
    imageUrl: String = "https://yt3.ggpht.com/ytc/AKedOLR5NNn9lbbFQqPkHCTMgvgvCjZi94G2JRU7DjsM=s900-c-k-c0x00ffffff-no-rj" ,
    titleColor: Color = Color.Black,
    text: String = "넹 좋아요",
    time: String = "오후 2:32",
    alpha: Float = 1f
){
    Card(
        shape = RoundedCornerShape(26.dp),
        modifier = modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(21.dp)
                .height(52.dp)
        ) {
            ChatImage(
                imageUrl = imageUrl,
                modifier = Modifier.weight(1f),
                alpha = alpha
            )
            
            Spacer(modifier = Modifier.width(18.dp))
            
            Column(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                ,
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = titleColor
                )
                Text(
                    text = text,
                    maxLines = 1,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF858585)
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
            Text(
                text = time,
                maxLines = 1,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF858585)
            )
        }
    }
}

@Composable
fun ChatNumber(
    modifier: Modifier,
    number: Int = 5
){
    Text(
        "$number",
        modifier = modifier
            .padding(end = 21.dp, bottom = 17.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(mainYellow, CircleShape),
        color = Color.White,
        textAlign = TextAlign.Center
    )
}


@Composable
fun ChatImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    alpha: Float
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.food),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(shape = RoundedCornerShape(15.dp)),
        alpha = alpha
    )

}


@Preview
@Composable
fun ChatPreviewItemNew(){
    ChattingItemNew()
}

@Preview
@Composable
fun ChatPreviewItem(){
    ChattingItem()
}

@Preview
@Composable
fun ChatPreviewItemDepre(){
    ChattingItem(
        modifier = Modifier,
    title = "삼첩분식 드실 분~ ㅁㄴㅇㄹㅁㄴ",
    imageUrl = "https://yt3.ggpht.com/ytc/AKedOLR5NNn9lbbFQqPkHCTMgvgvCjZi94G2JRU7DjsM=s900-c-k-c0x00ffffff-no-rj" ,
    titleColor = Color.Gray,
    text = "넹 좋아요",
    time = "오후 2:32",
        alpha = 0.3f
    )
}