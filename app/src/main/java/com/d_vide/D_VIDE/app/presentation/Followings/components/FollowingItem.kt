package com.d_vide.D_VIDE.app.presentation.Followings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.gray4
import com.d_vide.D_VIDE.ui.theme.main1

@Composable
fun FollowingItem(
    onClick: () -> Unit = {},
    userName: String = "룡룡",
    profileUrl: String = "https://image-notepet.akamaized.net/resize/620x-/seimage/20200320%2Fc69c31e9dde661c286a3c17201c79d35.jpg",
    modifier: Modifier = Modifier,
    isFollowing: Boolean = true
){
    Row(
          modifier = modifier
              .fillMaxWidth()
              .height(50.dp),
          verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier.clickable(onClick = onClick)
        ){
            FollowerProfileImage(imageUrl = profileUrl)
            Text(
                text = userName,
                style = TextStyles.Basics4,
                modifier = Modifier.padding(start = 9.dp).align(CenterVertically)
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            FollowDeleteButton(
                modifier = Modifier.size(60.dp, 23.dp).align(CenterEnd),
                buttonText = if (isFollowing) "팔로잉" else "삭제"
            )
        }
    }
}

@Composable
fun FollowerProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "character_circle",
        placeholder = painterResource(R.drawable.character_circle),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            /* default image일 때만 추가
            .border(
                width = 1.dp,
                color = mainYellow,
                shape = CircleShape
            )*/
    )
}
@Composable
fun FollowDeleteButton(
    modifier: Modifier = Modifier,
    buttonText: String = "팔로잉",
){
    var isClicked by remember{ mutableStateOf(false) }
    Button(
        onClick = {
            isClicked = !isClicked
        },
        shape = RoundedCornerShape(11.dp),
        modifier = modifier.size(60.dp, 23.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(if(isClicked) main1 else gray4)
    ) {
        Text(
            text = if(isClicked) "팔로우" else buttonText,
            color = Color.White,
            style = TextStyles.Basics2,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(CenterVertically)
        )
    }
}