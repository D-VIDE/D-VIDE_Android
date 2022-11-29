package com.d_vide.D_VIDE.app.presentation.ChattingDetail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.domain.model.ConversationUiState
import com.d_vide.D_VIDE.app.domain.model.Message
import com.d_vide.D_VIDE.app.presentation.ChattingDetail.component.UserInput
import com.d_vide.D_VIDE.app.presentation.Reviews.RecommendRow
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.GradientComponent
import com.d_vide.D_VIDE.app.presentation.view.ChattingDetail.ChattingDetailViewModel
import com.d_vide.D_VIDE.app.presentation.view.ChattingDetail.component.Messages
import com.d_vide.D_VIDE.app.presentation.view.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.BottomSheetUserFeedScreen
import com.d_vide.D_VIDE.app.presentation.view.UserFeed.UserFeedViewModel
import com.d_vide.D_VIDE.app.presentation.view.component.RecruitingWriteButton
import com.d_vide.D_VIDE.app.presentation.view.component.TopBarChatting
import com.d_vide.D_VIDE.app.presentation.view.component.TopRoundBarWithImage
import com.d_vide.D_VIDE.ui.theme.gray6
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChattingDetail(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    chattingId: Int,
    upPress: () -> Unit = {},
    viewModel: ChattingDetailViewModel = hiltViewModel(),
    userFeedViewModel: UserFeedViewModel = hiltViewModel(),
) {
    val userId = rememberSaveable{ mutableStateOf(0L) }


    BottomSheetUserFeedScreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick,
        userId = userId.value
    ) { state, scope ->
        ConversationContent(
            navigateToProfile = {
                userId.value = it.toLong()
                scope.launch {
                    userFeedViewModel.getOtherUserInfo(it.toLong())
                    state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                }
            },
            uiState = viewModel.state,
            // Add padding so that we are inset from any navigation bars
            modifier = Modifier.windowInsetsPadding(
                WindowInsets
                    .navigationBars
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            ),
            onMessageSent = { content ->
                viewModel.send(content)
            },
            upPress = upPress
        )
        BackHandler (
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded ||
                    state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch{
                    state.animateTo(ModalBottomSheetValue.Hidden, tween(300))
                }
            }
        )
    }



}


@Composable
fun ConversationContent(
    uiState: ConversationUiState,
    navigateToProfile: (String) -> Unit,
    modifier: Modifier = Modifier,
    upPress: () -> Unit = {},
    onMessageSent: (String) -> Unit
){
    val scrollState = rememberLazyListState()
    //val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(scrollState) }
    val scope = rememberCoroutineScope()
    var isExpand by remember { mutableStateOf(false) }

    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.background_chat),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )


            Column(
                Modifier
                    .fillMaxSize()
                //.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                Messages(
                    users = uiState.users,
                    messages = uiState.messages,
                    navigateToProfile = navigateToProfile,
                    modifier = Modifier.weight(1f),
                    scrollState = scrollState
                )
                UserInput(
                    onMessageSent = onMessageSent,
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
                    btnClick = { isExpand = !isExpand },
                    isExpand = isExpand
                )
                if(isExpand) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)

                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_camera),
                            contentDescription = "camera"
                        )
                        Spacer(modifier = Modifier.size(40.dp))
                        Image(
                            painterResource(id = R.drawable.ic_picture),
                            contentDescription = "review_title"
                        )
                        Spacer(modifier = Modifier.size(40.dp))
                        Image(
                            painterResource(id = R.drawable.ic_bill),
                            contentDescription = "review_title"
                        )
                    }
                }
            }
            // Channel name bar floats above the messages
            TopBarChatting(
                text = uiState.channelName,
                upPress = { upPress(); isExpand = false }
            )

        }
    }
}
