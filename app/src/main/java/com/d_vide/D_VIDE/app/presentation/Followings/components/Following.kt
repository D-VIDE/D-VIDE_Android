package com.d_vide.D_VIDE.app.presentation.Followings.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.mainYellow
import com.d_vide.D_VIDE.ui.theme.main_gray1

@Composable
fun Following(
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
    Button(
        onClick = {
            // DELETE followers here
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = main_gray1),
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
