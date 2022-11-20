package com.d_vide.D_VIDE.app.presentation.view.TaggedReviews.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.view.component.CardEndRound
import com.d_vide.D_VIDE.app.presentation.view.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.util.LikeButton
import com.d_vide.D_VIDE.ui.theme.*


@Composable
fun ReviewItem(
    onUserClick: () -> Unit={},
    onReviewClick: () -> Unit,
    onTagClick: (String) -> Unit,
    onLikeClick: () -> Unit = {},
    isLiked: Boolean = true,
    userImageURL: String = "",
    userName: String = "",
    reviewTitle : String = "",
    reviewText : String = "",
    reviewImage: String = "",
    starRating: Double = 5.0
){
    var liked by remember { mutableStateOf(isLiked) }
    CardEndRound(
        onClick = onReviewClick,
        modifier = Modifier.padding(end = 20.dp, start = if(liked) 0.dp else 20.dp)
    ) {
        Row() {
            //좋아요 클릭시 왼쪽 색상 변경
            if(liked){
                Surface(
                    color = main0,
                    modifier = Modifier
                        .width(20.dp)
                        .height(146.dp)
                ){}
            }

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
                    /**
                     * userImageURL: String = "", userId: String = "userId", userAddress: String = "userAddress"
                     */
                    UserInfo(
                        onClick = onUserClick,
                        modifier = Modifier.weight(1f),
                        userImageURL = userImageURL,
                        userId = userName
                    )
                    LikeButton(
                        onClick = {onLikeClick(); liked = !liked},
                        modifier = Modifier.size(17.dp, 15.dp),
                        isClicked = liked
                    )
                }

                /**
                 * reviewTitle : 식당 이름
                 * reviewText : 리뷰 본문
                 * reviewImage: 리뷰 이미지
                 */
                Review(
                    onTagClick = onTagClick,
                    reviewTitle = reviewTitle,
                    reviewText = reviewText,
                    reviewImage = reviewImage,
                    starRating = starRating
                )
            }
        }
    }
}

@Composable
fun Review(
    modifier: Modifier = Modifier,
    reviewTitle: String = "금돼지식당 복천점",
    reviewText: String = "금돼지식당 드실분~제가 LA에 있을때는 말이죠 정말 제가 꿈에무대 메이저리그로 진출하고 식당마다 싸인해달라 기자들은 항상 붙어다니며 ...",
    reviewImage: String = "https://img.siksinhot.com/article/1622690644980620.jpg",
    onTagClick: (String) -> Unit,
    starRating: Double = 5.0
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
            Text( text = "# ${reviewTitle}", maxLines = 1, style = TextStyles.Point2, color = red0, modifier = Modifier.clickable { onTagClick(reviewTitle) })
            Text( text = reviewText, maxLines = 3, style = TextStyles.Small1, color = gray3, modifier = Modifier.height(40.dp))
            Text(text = "⭐".repeat(starRating.toInt()), style = TextStyles.Basics1)
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

