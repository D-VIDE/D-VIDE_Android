package com.d_vide.D_VIDE.app.presentation.TaggedReviews

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.BottomSheetUserFeedSreen
import com.d_vide.D_VIDE.app.presentation.component.BottomNavigationBar
import com.d_vide.D_VIDE.app.presentation.component.FloatingButton
import com.d_vide.D_VIDE.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaggedReviewsScreen(
    Tag: String,
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
) {
    BottomSheetUserFeedSreen(
        navController = navController,
        onReviewSelected = onReviewSelected,
        onTagClick = onTagClick
    ) { state, scope ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
        ) {
            TagTitle(title = Tag)
            LazyColumn(
                contentPadding = PaddingValues(vertical = 28.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                item {
                    ReviewItem(
                        onUserClick = {
                            scope.launch {
                                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
                            }
                        },
                        onReviewClick = {onReviewSelected(1234)},
                        onTagClick = {onTagClick("금돼지식당 복천점")}
                    ) }


            }
        }
    }
}

@Composable
fun TagTitle(title: String = "금돼지 식당") {
    Surface(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .border(2.dp, main0, RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)),
        color = gray7,
        shape = RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp)
    ) {
        Text(
            text = "#금돼지식당 복천점",
            style = TextStyles.Big1,
            color = gray3,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun Preview() {
//    TaggedReviewsScreen(rememberNavController())
}