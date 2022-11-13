package com.d_vide.D_VIDE.app.presentation.MyReviews

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.MyPage.MyPageScreen
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.util.MoreButton
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.gray2
import kotlinx.coroutines.launch

@Composable
fun MyReviewsScreen(
    navController: NavController,
    onReviewSelected: (Int) -> Unit,
    onTagClick: (String) -> Unit,
    upPress: () -> Unit = {}
){
    val viewModel  = hiltViewModel<MyReviewsViewModel>()
    val reviews  =  viewModel.state.value.reviewsDTO

    Column(
        modifier = Modifier
            .background(White)
            .padding(20.dp, 23.dp, 0.dp, 20.dp)
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.up_press_gray),
                contentDescription = "upPress",
                modifier = Modifier
                    .size(12.dp, 19.dp)
                    .clickable(onClick = upPress)
            )
            Text(
                text = "룡룡님 피드",
                modifier = Modifier
                    .padding(start = 18.dp)
                    .align(CenterStart),
                style = TextStyles.Basics4
            )
            MoreButton(
                modifier = Modifier
                    .padding(end = 18.dp)
                    .align(CenterEnd)
                    .rotate(90f),
                tint = gray2
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(vertical = 28.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(reviews) { item ->
                ReviewItem(
                    onReviewClick = { onReviewSelected(item.review.reviewId.toInt()) },
                    onTagClick = { onTagClick(item.review.storeName) },
                    isLiked = item.review.isLiked
                )
            }
        }
    }
}