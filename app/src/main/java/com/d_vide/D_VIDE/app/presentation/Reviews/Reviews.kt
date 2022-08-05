package com.d_vide.D_VIDE.app.presentation.Reviews

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app.presentation.Chattings.ChattingItem
import com.d_vide.D_VIDE.app.presentation.Chattings.ChattingItemNew
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.Review
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.component.TopRoundBar
import com.d_vide.D_VIDE.ui.theme.mainOrange



@Composable
fun Reviews(
    navController: NavController
){
    Scaffold(
        topBar = { TopRoundBar("D/VIDE맛집") },
    ){
        LazyColumn(
            modifier = Modifier.padding(end = 20.dp),
            contentPadding = PaddingValues(top = 28.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                RecommendRow()
            }

            item { ReviewItem() }
            item { ReviewItem() }
            item { ReviewItem() }
            item { ReviewItem() }
            item { ReviewItem() }
            item { ReviewItem() }


        }
        it
    }
}

