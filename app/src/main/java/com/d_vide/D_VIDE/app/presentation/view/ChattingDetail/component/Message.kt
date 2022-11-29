package com.d_vide.D_VIDE.app.presentation.view.ChattingDetail.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.domain.model.ChatUserInfo
import com.d_vide.D_VIDE.app.domain.model.Message
import com.d_vide.D_VIDE.app.presentation.state.UserInformation
import com.d_vide.D_VIDE.app.presentation.util.convertTimestampToTime
import com.d_vide.D_VIDE.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun Messages(
    users: MutableMap<String, ChatUserInfo>,
    messages: List<Message>,
    navigateToProfile: (String) -> Unit,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {

        //자신의 아이디값을 얻음
        val authorMe by mutableStateOf(UserInformation.userInfo.userId.toString() + "userId")
        LazyColumn(
            reverseLayout = true,
            state = scrollState,
            // Add content padding so that the content can be scrolled (y-axis)
            // below the status bar + app bar
            // TODO: Get height from somewhere
            contentPadding =
            WindowInsets.statusBars.add(WindowInsets(top = 55.dp)).asPaddingValues(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            for (index in messages.indices) {
                val prevAuthor = messages.getOrNull(index - 1)?.author
                val nextAuthor = messages.getOrNull(index + 1)?.author
                val content = messages[index]
                val isFirstMessageByAuthor = prevAuthor != content.author
                val isLastMessageByAuthor = nextAuthor != content.author

                // Hardcode day dividers for simplicity
                // 날짜 구분
                if (index == messages.size - 1) {
                    item {
                        DayHeader("20 Aug")
                    }
                } else if (index == 2) {
                    item {
                        DayHeader("Today")
                    }
                }
                //메세지 보여주는 곳
                item {
                    Message(
                        author = users[content.author]?.nickname ?: "",
                        onAuthorClick = { name -> navigateToProfile(name) },
                        msg = content,
                        isUserMe = content.author == authorMe,
                        isFirstMessageByAuthor = isFirstMessageByAuthor,
                        isLastMessageByAuthor = isLastMessageByAuthor
                    )
                }
            }
        }
        // Jump to bottom button shows up when user scrolls past a threshold.
        // Convert to pixels:
        val jumpThreshold = with(LocalDensity.current) {
            JumpToBottomThreshold.toPx()
        }

        // Show the button if the first visible item is not the first one or if the offset is
        // greater than the threshold.
        val jumpToBottomButtonEnabled by remember {
            derivedStateOf {
                scrollState.firstVisibleItemIndex != 0 ||
                        scrollState.firstVisibleItemScrollOffset > jumpThreshold
            }
        }

        JumpToBottom(
            // Only show if the scroller is not at the bottom
            enabled = jumpToBottomButtonEnabled,
            onClicked = {
                scope.launch {
                    scrollState.animateScrollToItem(0)
                }
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}



@Composable
fun Message(
    author: String = "",
    onAuthorClick: (String) -> Unit,
    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean
) {
    val borderColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    val spaceBetweenAuthors = if (isLastMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthors) {
        if (isLastMessageByAuthor && !isUserMe) {
            // 프로필 사진
            Image(
                modifier = Modifier
                    .clickable(onClick = { onAuthorClick(msg.author.replace("userId", "")) })
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painterResource(id = R.drawable.character_circle),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            // Space under avatar
            Spacer(modifier = Modifier.width(74.dp))
        }
        AuthorAndTextMessage(
            author = author,
            msg = msg,
            isUserMe = isUserMe,
            isFirstMessageByAuthor = isFirstMessageByAuthor,
            isLastMessageByAuthor = isLastMessageByAuthor,
            authorClicked = onAuthorClick,
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
    }
}

@Composable
fun AuthorAndTextMessage(
    author: String = "",
    msg: Message,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean,
    authorClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        if (isLastMessageByAuthor && !isUserMe) {
            AuthorName(author)
        }
        Row(Modifier.align(if (isUserMe) Alignment.End else Alignment.Start)) {
            if (isUserMe) {
                MessageTime(msg = msg,
                    Modifier
                        .padding(bottom = 4.dp)
                        .align(Alignment.Bottom)
                        .width(40.dp))
                Spacer(modifier = Modifier.size(3.dp))
                ChatItemBubble(msg, isUserMe, authorClicked = authorClicked)
            } else {
                ChatItemBubble(msg, isUserMe, authorClicked = authorClicked)
                Spacer(modifier = Modifier.size(3.dp))
                MessageTime(msg = msg,
                    Modifier
                        .padding(bottom = 4.dp)
                        .align(Alignment.Bottom)
                        .width(40.dp))
            }
        }
        if (isFirstMessageByAuthor) {
            // Last bubble before next author
            Spacer(modifier = Modifier.height(8.dp))
        } else {
            // Between bubbles
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

/**
 * 보낸 사람을 표시 하는 부분
 */
@Composable
private fun AuthorName(author: String) {
    // Combine author and timestamp for a11y.
    Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Text(
            text = author,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp) // Space to 1st bubble
        )

    }
}

@Composable
private fun MessageTime(msg: Message, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = msg.timestamp.convertTimestampToTime(),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}


@Composable
fun DayHeader(dayString: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .height(16.dp)
    ) {
        DayHeaderLine()
        Text(
            text = dayString,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        DayHeaderLine()
    }
}

@Composable
private fun RowScope.DayHeaderLine() {
    Divider(
        modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}


/**
 * 메세지 UI를 그리는 코드
 * @param message 입력되는 메세지
 * @param isUserMe 보낸사람이 나 일 경우(true) 아닐경우 (false)
 * @param authorClicked 보낸사람을 클릭했을 경우 작동하는 함수
 */
@Composable
fun ChatItemBubble(
    message: Message,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {
    //디바이스 스크린 width 구하기
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    //shape color(배경색)
    val backgroundBubbleColor = if (isUserMe) {
        main1
    } else {
        gray7
    }

    val backgroundShape = if (isUserMe) {
        RoundedCornerShape(30.dp, 0.dp, 30.dp, 30.dp)
    } else {
        RoundedCornerShape(0.dp, 30.dp, 30.dp, 30.dp)
    }

    Column(Modifier.widthIn(0.dp, screenWidth - 40.dp - 74.dp - 16.dp)) {
        Surface(
            color = backgroundBubbleColor,
            shape = backgroundShape,
            border = BorderStroke(if (isUserMe) 0.dp else 2.dp, main0),
            shadowElevation = 3.dp
        ) {
            ClickableMessage(
                message = message,
                isUserMe = isUserMe,
                authorClicked = authorClicked
            )
        }


        //이미지일 경우 메세지
        message.image?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                color = backgroundBubbleColor,
                shape = backgroundShape,
                border = BorderStroke(2.dp, main0)
            ) {
                Image(
                    painter = painterResource(it),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(160.dp),
                    contentDescription = stringResource(id = 0)
                )
            }
        }
    }
    

}

@Composable
fun ClickableMessage(
    message: Message,
    isUserMe: Boolean,
    authorClicked: (String) -> Unit
) {

    val textColor = if (isUserMe) {
        gray7
    } else {
        gray5
    }

    val uriHandler = LocalUriHandler.current

    val styledMessage = messageFormatter(
        text = message.content,
        primary = isUserMe
    )

    ClickableText(
        text = styledMessage,
        style = MaterialTheme.typography.bodyLarge.copy(color = textColor),
        modifier = Modifier.padding(16.dp),
        onClick = {
            styledMessage
                .getStringAnnotations(start = it, end = it)
                .firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
                        SymbolAnnotationType.PERSON.name -> authorClicked(annotation.item)
                        else -> Unit
                    }
                }
        }
    )
}


private val JumpToBottomThreshold = 56.dp