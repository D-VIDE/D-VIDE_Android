package com.d_vide.D_VIDE.app.presentation.view.UserFeed

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetUserFeedScreen(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    userId: Long = 0L,
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
) {
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        sheetState = state,
        sheetContent = {
            UserFeedScreen(
                navController = navController,
                modifier = Modifier.fillMaxHeight(0.945f),
                onReviewSelected = onReviewSelected,
                onTagClick = onTagClick,
                userId = userId
            )
        }
    ) {
        activityContentScope(state, scope)
    }
}
