package com.d_vide.D_VIDE.app.presentation.Reviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun RecommendRow(
    onTagClick: (String) -> Unit
){

    LazyRow(
        contentPadding = PaddingValues(horizontal = 19.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ){
        item { RecommendItem(onTagClick = {onTagClick("test")}) }
        item { RecommendItem(onTagClick = {onTagClick("test")}) }

    }
}



@Composable
fun RecommendItem(onTagClick: () -> Unit){
    Card(
        modifier = Modifier
            .size(106.dp, 137.dp)
            .clickable(onClick = onTagClick),
        shape = RoundedCornerShape(13.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecommendItemImage()
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "1위",
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = mainOrange,
            )
            Text(
                text = "한남점 토스트",
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF787878) ,
            )
        }
    }
}


@Composable
fun RecommendItemImage(){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://yt3.ggpht.com/ytc/AKedOLR5NNn9lbbFQqPkHCTMgvgvCjZi94G2JRU7DjsM=s900-c-k-c0x00ffffff-no-rj")
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.food),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp, 98.dp)
            .clip(shape = RoundedCornerShape(15.dp)),
    )
}
