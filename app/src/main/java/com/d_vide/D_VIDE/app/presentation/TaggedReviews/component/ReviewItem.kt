package com.d_vide.D_VIDE.app.presentation.TaggedReviews.component

import android.media.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.component.CardEndRound
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.ui.theme.*


/**
 * 1. model 정해지면 model 인자로 받도록 수정
 * 2. shape 추가 가능하면 추가하고 card shape 수정
 * 3. design, color나오면 card인자 전체 수정
 */



@Composable
fun ReviewItem(
    onUserClick: () -> Unit={},
    onReviewClick: () -> Unit
){
    CardEndRound(onClick = onReviewClick) {
        Row() {
            Surface(
                color = main0,
                modifier = Modifier
                    .width(20.dp)
                    .height(146.dp)
            ){}

            Column(
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 15.dp, start = 19.dp, end = 19.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserInfo(onClick = onUserClick)
                    IconButton(
                        onClick = {},
                        modifier = Modifier.size(17.dp, 15.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                        )
                    }
                }
                Review()
            }
        }
    }
}

@Composable
fun Review(
    modifier: Modifier = Modifier,
    reviewTitle: String = "금돼지식당 복천점",
    reviewText: String = "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 ...",
    reviewImage: String = "https://img.siksinhot.com/article/1622690644980620.jpg"
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DivideImage(
            modifier = Modifier
                .size(86.dp)
                .clip(RoundedCornerShape(13.dp)),
            imageURL = reviewImage
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Text( text = "# ${reviewTitle}", maxLines = 1, style = TextStyles.Point2, color = red0)
            Text( text = reviewText, maxLines = 3, style = TextStyles.Small1, color = gray3, modifier = Modifier.height(40.dp))
            Text(text = "⭐⭐⭐⭐⭐️", style = TextStyles.Basics1)
        }
    }
}




/**
 * UserInfo
 */
@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    userImageURL: String = "",
    userId: String = "userId",
    userAddress: String = "userAddress"
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            modifier = Modifier.clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            DivideImage(
                modifier = Modifier
                    .size(28.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    ),
                imageURL = userImageURL
            )
            Text( text = userId, maxLines = 1, style = TextStyles.Basics1, color = gray5)
            Text( text = userAddress, maxLines = 1, style = TextStyles.Small1, color = gray2)
        }

    }
}

