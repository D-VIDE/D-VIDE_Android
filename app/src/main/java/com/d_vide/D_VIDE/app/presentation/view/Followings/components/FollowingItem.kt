package com.d_vide.D_VIDE.app.presentation.view.Followings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.view.Followings.FollowViewModel
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun FollowingItem(
    onUserClick: () -> Unit = {},
    userName: String = "룡룡",
    profileUrl: String? = "https://image-notepet.akamaized.net/resize/620x-/seimage/20200320%2Fc69c31e9dde661c286a3c17201c79d35.jpg",
    modifier: Modifier = Modifier,
    isFollowing: Boolean = true,
    userId: Long = 0L,
    followId: Long = 0L,
    followed: Boolean = true
){
    Row(
          modifier = modifier
              .fillMaxWidth()
              .height(50.dp),
          verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier.clickable(onClick = onUserClick)
        ){
            FollowerProfileImage(imageUrl = profileUrl)
            Text(
                text = userName,
                style = TextStyles.Basics4,
                modifier = Modifier
                    .padding(start = 9.dp)
                    .align(CenterVertically)
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            FollowDeleteButton(
                modifier = Modifier
                    .size(60.dp, 23.dp)
                    .align(CenterEnd),
                isFollowing = isFollowing,
                userId = userId,
                followId = followId,
                followed = followed
            )
        }
    }
}

@Composable
fun FollowerProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = ""
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
    isFollowing: Boolean = false,
    userId: Long = 0L,
    followId: Long = 0L,
    followed: Boolean = false
){
    var isClicked by remember { mutableStateOf(followed) }
    var isDialogOpen by remember { mutableStateOf(false) }
    val viewModel = hiltViewModel<FollowViewModel>()
    Button(
        onClick = {
            isClicked = !isClicked
            if (isFollowing && !isClicked) {
                "언팔하는 중".log()
                viewModel.deleteFollow(followId.toInt())
            }
            if (isFollowing && isClicked){
                "팔로잉하는 중".log()
                viewModel.postFollow(userId)
            }
            if (!isFollowing){
                "팔로워 삭제하는 중".log()
                isDialogOpen = !isDialogOpen
            }
        },
        shape = RoundedCornerShape(11.dp),
        modifier = modifier.size(60.dp, 23.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(if(!isClicked && isFollowing) main1 else gray4)
    ) {
        Text(
            text = if (!isFollowing) "삭제" else if (isClicked) "팔로잉" else "팔로우",
            color = Color.White,
            style = TextStyles.Basics2,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(CenterVertically)
        )
    }
    if (isDialogOpen) ConfirmDeletePopUp(onDismiss = { isDialogOpen = !isDialogOpen }, onDelete = { viewModel.deleteFollow(followId.toInt()) })
}
@Composable
fun ConfirmDeletePopUp(
    onDismiss: () -> Unit = {},
    onDelete: () -> Unit = {}
){
    Dialog(
        onDismissRequest = onDismiss,
    ){
        Column(
            modifier = Modifier.width(312.dp),
            verticalArrangement = Arrangement.Center
        ){
            Column(
                modifier = Modifier.fillMaxWidth().background(White),
                horizontalAlignment = CenterHorizontally,
            ){
                Text(
                    text = "정말 팔로워를 삭제하시겠어요?",
                    style = TextStyles.Point5,
                    modifier = Modifier
                        .height(102.dp)
                        .padding(top = 59.dp, bottom = 27.dp),
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier.fillMaxWidth(), color = gray1_1)
                Text(
                    text = "삭제",
                    style = TextStyles.Point5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(69.dp)
                        .clickable {
                            onDelete()
                            onDismiss()
                        }
                        .padding(top = 20.dp, bottom = 33.dp),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "취소",
                style = TextStyles.Point5,
                modifier = Modifier
                    .height(62.dp)
                    .clickable(onClick = onDismiss)
                    .fillMaxWidth()
                    .background(White)
                    .padding(vertical = 23.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}