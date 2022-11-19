package com.d_vide.D_VIDE.app.presentation.Reviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.RecommendStore
import com.d_vide.D_VIDE.app.presentation.view.component.CardRoundSmall
import com.d_vide.D_VIDE.app.presentation.view.component.DivideImage
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.gray9
import com.d_vide.D_VIDE.ui.theme.main1
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun RecommendRow(
    onTagClick: (String) -> Unit,
    data: List<RecommendStore>
){
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text("• 디/바이더 추천 맛집!", color = gray9, style = TextStyles.Point2, modifier = Modifier.padding(start = 20.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 19.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ){
            itemsIndexed(data){ index, item ->
                RecommendItem(
                    onTagClick = {onTagClick(item.storeName)},
                    imageURL = item.reviewImgUrl,
                    Rank = index + 1,
                    Title = item.storeName
                )
            }

        }
    }
}



@Composable
fun RecommendItem(
    onTagClick: () -> Unit,
    imageURL: String,
    Rank: Int,
    Title: String
){

    CardRoundSmall(
        onClick = onTagClick
    ) {
        Column(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DivideImage(
                modifier = Modifier
                    .size(100.dp, 98.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                imageURL = imageURL)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "${Rank}위", color = main1,  style = TextStyles.Small3)
            Text(text = Title, color = gray9, style = TextStyles.Small0)
        }
    }

}


