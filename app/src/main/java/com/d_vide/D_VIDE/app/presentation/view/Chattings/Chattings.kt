package com.d_vide.D_VIDE.app.presentation.view.Chattings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.Surface
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.domain.model.Chat
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.app.presentation.view.component.CardRound
import com.d_vide.D_VIDE.app.presentation.view.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBar
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun Chattings(
    navController: NavController,
    onChattingSelected: (Long) -> Unit,
    viewModel: ChattingsViewModel = hiltViewModel()
){
    Scaffold(
        topBar = { TopRoundBar(text = "D/VIDE 채팅") },
    ){ it ->
        Surface(
            color = gray6,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box() {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 15.dp, horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(9.dp),
                ) {

                    items(viewModel.chatList) { item ->
                        ChattingItem(
                            onChattingSelected = {onChattingSelected(item.first)},
                            chatroom = item.second,
                            userId = viewModel.userId
                        )
                    }

                    item{
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }
                GradientComponent(Modifier.align(Alignment.BottomCenter))
            }
        }
        it
    }
}


@Composable
fun ChattingItem(
    chatroom: Chat,
    onChattingSelected: () -> Unit,
    userId: String
){
    Box(
        modifier = Modifier.clickable(onClick = onChattingSelected)
    ) {
        if(chatroom.users[userId]?.unRead == true) {
            ChattingItem(
                Modifier.border(2.dp, mainYellow, RoundedCornerShape(25.dp)),
                onChattingSelected = onChattingSelected,
                title = chatroom.title
            )
            ChatNumber(
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        } else {
            ChattingItem(
                onChattingSelected = onChattingSelected,
                title = chatroom.title,
                text = chatroom.lastMessage.content
            )
        }
    }

}


@Composable
fun ChattingItem(
    modifier: Modifier = Modifier,
    title: String = "삼첩분식 드실 분~ ㅁㄴㅇㄹㅁㄴ",
    imageUrl: String = "https://yt3.ggpht.com/ytc/AKedOLR5NNn9lbbFQqPkHCTMgvgvCjZi94G2JRU7DjsM=s900-c-k-c0x00ffffff-no-rj" ,
    titleColor: Color = Color.Black,
    text: String = "넹 좋아요",
    time: String = "오후 2:32",
    alpha: Float = 1f,
    onChattingSelected: () -> Unit
){
    CardRound(
        onClick = onChattingSelected,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(17.dp)
        ) {
            DivideImage(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(15.dp)),
                imageURL = imageUrl,
                alpha = alpha
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = title,
                        maxLines = 1,
                        style = TextStyles.Point4,
                        overflow = TextOverflow.Ellipsis,
                        color = gray5.copy(alpha = alpha),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = time,
                        maxLines = 1,
                        style = TextStyles.Small1,
                        color = gray11.copy(alpha = alpha),
                    )
                }
                Text(
                    text = text,
                    maxLines = 1,
                    style = TextStyles.Basics1,
                    overflow = TextOverflow.Ellipsis,
                    color = gray10.copy(alpha = alpha)
                )
            }
        }
    }
}

@Composable
fun ChatNumber(
    modifier: Modifier,
    number: Int = 5
){
    Text(
        "$number",
        modifier = modifier
            .padding(end = 27.dp, bottom = 19.dp)
            .size(20.dp)
            .clip(CircleShape)
            .background(main0, CircleShape),
        style = TextStyles.Basics3,
        color = gray7,
        textAlign = TextAlign.Center
    )
}




