package com.d_vide.D_VIDE.app.presentation.ReviewDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.component.CardEndRound
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.app.presentation.util.MoreButton
import com.d_vide.D_VIDE.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun ReviewDetail(
    reviewId: Int,
    upPress:() -> Unit,
    onTagClick:(String) -> Unit
){
    Scaffold(
        topBar = { TopRoundBar("리뷰", onClick = upPress) },
    ){ innerPadding ->
            Column(
                Modifier.verticalScroll(rememberScrollState())
            ) {
                ReviewCard(
                    imageURL = "https://i.ytimg.com/vi/9ONqnsb2adI/maxresdefault.jpg",
                    userId = "userId",
                    address = "address",
                    //ImageList = ,
                    like = 1234,
                    tag = "tag",
                    body = "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 금돼지식당 드실분~\n" +
                            "\n" +
                            "제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 가나다라 마바사!!\n" +
                            "\n" +
                            "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며!\n" +
                            "\n" +
                            "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 ....\n" +
                            "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며!\n",
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp),
                    onTagClick = onTagClick
                )
            }
    }
}

@Composable
fun ReviewCard(
    imageURL: String,
    userId: String,
    address: String,
    //ImageList: List<String>,
    like: Int,
    tag: String,
    body: String,
    modifier: Modifier = Modifier,
    onTagClick:(String) -> Unit
){
    CardEndRound(
        modifier = modifier,
        enabled = false
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(11.dp),
            modifier = Modifier.padding(horizontal = 19.dp, vertical = 13.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(9.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                DivideImage(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    imageURL = imageURL
                )
                
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(text = userId, style = TextStyles.Basics1)
                        Text(text = "⭐⭐⭐⭐⭐️", style = TextStyles.Basics1)
                    }
                    Text(text = address, style = TextStyles.Small1, color = gray2, modifier = Modifier.padding(top = 1.dp))
                }

                MoreButton()
            }
            
            ImagePager(items = listOf("1", "2", "3"))
            
            
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "❤️️", style = TextStyles.Basics1)
                Text(text = "$like", style = TextStyles.Basics2, color = gray2)
            }

            Text(text = "# ${tag}", style = TextStyles.Point4, color = red0, modifier = Modifier.clickable(onClick = {onTagClick(tag)}))
            Text(text = body, style = TextStyles.Basics1, color = gray5)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePager(
    items: List<String>
){
    val pagerState = rememberPagerState()

    Column(
        verticalArrangement = Arrangement.spacedBy(7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = items.size,
            state = pagerState,
        ) { i ->
            DivideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                imageURL = "https://i.ytimg.com/vi/9ONqnsb2adI/maxresdefault.jpg")
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            spacing = 4.dp,
            indicatorHeight =5.dp,
            indicatorWidth = 5.dp,
            indicatorShape = CircleShape,
            activeColor = main0,
        )
    }
}


@Composable
@Preview
fun PreviewDetail(){
    //ReviewDetail(reviewId = 1234, {})
}