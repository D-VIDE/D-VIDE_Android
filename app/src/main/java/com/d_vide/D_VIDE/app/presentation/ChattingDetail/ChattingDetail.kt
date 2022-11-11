package com.d_vide.D_VIDE.app.presentation.ChattingDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.d_vide.D_VIDE.app.presentation.ChattingDetail.component.Messages
import com.d_vide.D_VIDE.app.presentation.ChattingDetail.component.UserInput
import com.d_vide.D_VIDE.app.presentation.component.TopBarChatting
import kotlinx.coroutines.launch

@Composable
fun ChattingDetail(
    chattingId: Int,
    upPress: () -> Unit = {}
) {
    ConversationContent(
        uiState = exampleUiState,
        navigateToProfile = { user ->
        },
        // Add padding so that we are inset from any navigation bars
        modifier = Modifier.windowInsetsPadding(
            WindowInsets
                .navigationBars
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        )
    )
}


@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    navigateToProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    upPress: () -> Unit = {}
){
    val authorMe = "authorMe"
    val timeNow = "timeNow"

    val scrollState = rememberLazyListState()
    //val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(scrollState) }
    val scope = rememberCoroutineScope()

    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    //.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                Messages(
                    messages = uiState.messages,
                    navigateToProfile = navigateToProfile,
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState
                )
                UserInput(
                    onMessageSent = { content ->
                        uiState.addMessage(
                            Message(authorMe, content, timeNow)
                        )
                    },
                    resetScroll = {
                        scope.launch {
                            scrollState.scrollToItem(0)
                        }
                    },
                    // Use navigationBarsPadding() imePadding() and , to move the input panel above both the
                    // navigation bar, and on-screen keyboard (IME)
                    modifier = Modifier
                        .navigationBarsPadding()
                        .imePadding(),
                )
            }
            // Channel name bar floats above the messages
            TopBarChatting(
                "채팅",
                upPress = upPress
            )


        }
    }
}
