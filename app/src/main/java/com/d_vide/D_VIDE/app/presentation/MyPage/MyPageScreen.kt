package com.d_vide.D_VIDE.app.presentation.MyPage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.mainYellow

@Composable
fun MyPageScreen(
    navController: NavController,
) {

}

@Composable
fun MyPageUserProfile() {
    Box(contentAlignment = Alignment.TopCenter) {
        UserProfileImage()

        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp)
                .border(2.dp, mainYellow, RoundedCornerShape(14.dp))
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(55.dp))
                Text(
                    text = "디바이드 공식 돼지",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = mainOrange,
                    modifier = Modifier.padding(top = 3.dp)
                )
                Text(
                    text = "룡룡",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Following()
                Spacer(modifier = Modifier.size(10.dp))
            }
        }

    }
}

@Composable
fun UserProfileImage() {
    Image(
        painterResource(id = R.drawable.character_circle),
        contentDescription = "character_circle",
        modifier = Modifier
            .size(107.dp)
            .shadow(elevation = 5.dp, shape = CircleShape)
            .zIndex(1F)
            .padding()
    )
}

@Preview
@Composable
private fun PreviewMyPageScreen() {
//    MyPageScreen(navController = rememberNavController())
//    UserProfile()
    MyPageUserProfile()
}


@Composable
fun Following(
    modifier: Modifier = Modifier,
    Following: Int = 6,
    Follower: Int = 3
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {/*do something*/ },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "6",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    color = Color(0xFF4D4D4D)
                )
                Text(
                    text = "팔로잉",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Divider(
                modifier = Modifier.size(1.dp, 31.dp),
                color = Color.Gray,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {/*do something*/ },
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "12",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    color = Color(0xFF4D4D4D)
                )
                Text(
                    text = "팔로워",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
