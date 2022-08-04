package com.d_vide.D_VIDE.app.presentation.Followings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.mainYellow
import com.d_vide.D_VIDE.ui.theme.main_gray1

@Composable
fun FollowingItem(
    modifier: Modifier = Modifier
){
    Row(
          modifier = modifier
              .fillMaxWidth()
              .height(66.dp),
          verticalAlignment = Alignment.CenterVertically
    ){
        FollowerProfileImage(imageUrl = "https://image-notepet.akamaized.net/resize/620x-/seimage/20200320%2Fc69c31e9dde661c286a3c17201c79d35.jpg")
        Text(
            text = "asdf dsd",
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ){
            FollowDeleteButton(Modifier.align(CenterEnd))
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
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .size(50.dp)
            .border(
                width = 1.dp,
                color = mainYellow,
                shape = CircleShape
            )
    )
}
@Composable
fun FollowDeleteButton(
    modifier: Modifier = Modifier
){
    var buttonColor by remember{ mutableStateOf(mainOrange) }

    Button(
        onClick = {
            buttonColor = main_gray1
            // DELETE followers here
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        shape = RoundedCornerShape(11.dp),
        modifier = modifier.size(47.dp, 23.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "삭제",
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
