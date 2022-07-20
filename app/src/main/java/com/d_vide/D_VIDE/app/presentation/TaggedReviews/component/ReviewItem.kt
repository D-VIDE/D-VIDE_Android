package com.d_vide.D_VIDE.app.presentation.TaggedReviews.component

import android.media.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.Typography
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.mainYellow


/**
 * 1. model 정해지면 model 인자로 받도록 수정
 * 2. shape 추가 가능하면 추가하고 card shape 수정
 * 3. design, color나오면 card인자 전체 수정
 */



@Composable
fun ReviewItem(){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topEnd = 30.dp,
            bottomEnd = 30.dp
        ),
        elevation = 3.dp

    ) {
       Column(
           modifier = Modifier
               .padding(10.dp)
       ) {
           Row(
               modifier = Modifier
                   .padding(3.dp)
                   .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
            UserInfo()
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null
                )
            }
           }
           Review(modifier = Modifier
               .padding(3.dp))
       }
    }
}

@Composable
fun Review(
    modifier: Modifier = Modifier,
    reviewTitle: String = "reviewTitle",
    reviewText: String = "reviewText",
    reviewImage: String = "reviewImage"
){
    Row(
        modifier = modifier
    ) {
        ReviewImage(imageURL = reviewImage)
        Column{
            ReviewTitle(reviewTitle = reviewTitle)
            Spacer(modifier = Modifier.size(3.dp))

            ReviewText(reviewText = reviewText)
            Spacer(modifier = Modifier.size(3.dp))

            Rating()
        }
    }
}



/**
 * Rating
 * 유저 주소 텍스트
 * user Model로 받아서 수정
 * style 변경 해야함
 */
@Composable
fun Rating(
    modifier: Modifier = Modifier
){
    Text(
        text = "★★★★★",
        style = MaterialTheme.typography.subtitle1,
        color = mainOrange,
        modifier = Modifier
    )
}

/**
 * ReviewText
 * review 본문
 * review Model로 받아서 수정
 * style 변경 해야함
 */
@Composable
fun ReviewText(
    reviewText: String ,
    modifier: Modifier = Modifier
){
    Text(
        text = reviewText,
        style = MaterialTheme.typography.body2,
        color = Color.Gray,
        modifier = Modifier,
        maxLines = 1
    )
}

/**
 * ReviewTitle
 * 식당 텍스트
 * revuew로 받아서 수정
 * style 변경 해야함
 */
@Composable
fun ReviewTitle(
    reviewTitle: String,
    modifier: Modifier = Modifier
){
    Text(
        text = "#$reviewTitle",
        style = MaterialTheme.typography.subtitle1,
        color = mainOrange,
        modifier = Modifier,
        maxLines = 1
    )
}

/**
 * UserInfo
 * 유저 정보 담는 부분 함수
 * 1. 유저 정보 model 나오면 싹다 변경
 */
@Composable
fun UserInfo(
    modifier: Modifier = Modifier,
    userImageURL: String = "",
    userId: String = "userId",
    userAddress: String = "userAddress"
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        ProfileImage(imageURL = userImageURL)
        UserId(userId = userId, Modifier.padding(horizontal = 10.dp))
        UserAddress(userAddress = userAddress, Modifier.padding(horizontal = 10.dp))
    }
}


/**
 * UserAddress
 * 유저 주소 텍스트
 * user Model로 받아서 수정
 * style 변경 해야함
 */
@Composable
fun UserAddress(
    userAddress: String,
    modifier: Modifier = Modifier
){
    Text(
        text = userAddress,
        style = MaterialTheme.typography.body2,
        color = Color.Gray,
        modifier = Modifier.padding(top = 2.dp),
        maxLines = 1
    )
}

/**
 * UserId
 * 유저 아이디 텍스트
 * user Model로 받아서 수정
 * style 변경 해야함
 */
@Composable
fun UserId(
    userId: String,
    modifier: Modifier
){
    Text(
        text = userId,
        style = MaterialTheme.typography.subtitle1,
        modifier = modifier,
        maxLines = 1
    )
}

/**
 * ReviewImage
 * 리뷰 이미지 함수
 * 1. contentDescription 수정
 * 2. border 속성 싹 다 수정
 */
@Composable
fun ReviewImage(
    modifier: Modifier = Modifier,
    imageURL: String
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.food),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(end = 10.dp)
            .size(100.dp, 100.dp)
            .clip(shape = RoundedCornerShape(20.dp))

    )
}


/**
 * ProfileImage
 * 프로필 이미지 함수
 * 1. contentDescription 수정
 * 2. border 속성 싹 다 수정
 */
@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageURL: String
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        contentDescription = null,
        placeholder = painterResource(R.drawable.character_circle),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(40.dp, 40.dp)
            .clip(shape = CircleShape)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = CircleShape
            )
    )
}

@Preview
@Composable
fun PreviewUserInfo(){
    ReviewItem()
}