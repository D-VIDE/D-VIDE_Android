package com.d_vide.D_VIDE.app.presentation.UserFeed.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.Recruitings.BlankIndicator
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    userName: String = "룡룡",
    userNickName: String = "디바이드 공식 돼지",
    Following: Int = 6,
    Follwer: Int = 3
){
    Box(
        modifier = modifier.size(349.dp, 83.dp)
    ){
        Row{
            MainProfile(Modifier.weight(0.6f))
            Column(
                Modifier.padding(start = 7.dp).weight(0.4f)
            ){
                Following(Modifier.padding(bottom = 9.dp))
                FollowingButton(Modifier.fillMaxWidth())
            }
        }

    }

}

@Composable
fun MainProfile(
    modifier: Modifier = Modifier,
    imageUrl: String = "https://image-notepet.akamaized.net/resize/620x-/seimage/20200320%2Fc69c31e9dde661c286a3c17201c79d35.jpg",
    userName: String = "룡룡",
    userNickName: String = "디바이드 공식 돼지"
){
    Box(
       modifier = modifier
    ){
        Box(
            modifier = Modifier
                .padding(start = 29.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(White)
        ) {
            Column(
                modifier = Modifier
                    .height(85.dp)
                    .padding(start = 64.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = userName,
                    style = TextStyles.Point4,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = userNickName,
                    textAlign = TextAlign.Center,
                    style = TextStyles.Small3,
                    color = mainOrange,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }

        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "character_circle",
            placeholder = painterResource(R.drawable.character_circle),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(82.dp)
                .border(
                    width = 3.dp,
                    color = mainYellow,
                    shape = CircleShape
                )
                .zIndex(1F),
        )
    }
}

@Composable
fun Following(
    modifier: Modifier = Modifier,
    Following: Int = 6,
    Follower: Int = 3
){
    Box(
        modifier = modifier
            .size(129.dp, 56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(White)
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier.weight(1f).clickable{/*do something*/},
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Following.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.4.sp,
                    color = Color(0xFF4D4D4D)
                )
                Text(
                    text = "팔로잉",
                    textAlign = TextAlign.Center,
                    style = TextStyles.Small4,
                    color = main_gray2
                )
            }
            Divider(
                modifier = Modifier.size(1.dp, 25.dp),
                color = main_gray1,
            )
            Column(
                modifier = Modifier.weight(1f).clickable{/*do something*/},
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = Follower.toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 0.4.sp,
                    color = Color(0xFF4D4D4D)
                )
                Text(
                    text = "팔로워",
                    textAlign = TextAlign.Center,
                    style = TextStyles.Small4,
                    color = main_gray2
                )
            }
        }
    }
}

@Composable
fun FollowingButton(
    modifier: Modifier = Modifier
){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(backgroundColor = mainOrange),
        shape = RoundedCornerShape(11.dp),
        modifier = modifier.size(129.dp, 22.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "팔로우",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = TextStyles.Small3,
            modifier = Modifier.align(CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    DVIDETheme {
        UserProfile()
    }
}