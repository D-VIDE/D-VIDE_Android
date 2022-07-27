package com.d_vide.D_VIDE.app.presentation.UserFeed

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.TaggedReviews.component.ReviewItem
import com.d_vide.D_VIDE.app.presentation.UserFeed.component.UserProfile
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.main_gray2
import com.d_vide.D_VIDE.ui.theme.main_gray3
import com.d_vide.D_VIDE.ui.theme.profile_gray
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun UserFeedScreen(
//    navController: NavController
){

    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize().background(main_gray2),
        state = state,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar ={
            Box(
                modifier = Modifier
                    .padding(start = 21.dp)
                    .fillMaxWidth()
                    .height(52.dp)
                    .pin()
            )
            IconButton(
                onClick = { /* doSomething() */ }
            ) {
               Image(
                   painterResource(id = R.drawable.navigate_up),
                   contentDescription = "뒤로가기",
                   modifier = Modifier.padding(top = 10.dp).size(11.dp, 20.dp)
               )
           }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                   if (state.toolbarState.height == state.toolbarState.maxHeight) RoundedCornerShape(36.dp, 36.dp, 0.dp, 0.dp)
                   else RoundedCornerShape(0.dp)
                )
                .background(profile_gray)
                .padding(horizontal = 18.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            UserProfile()
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .padding(bottom = 8.dp)
                    .height(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(
                    modifier = Modifier
                        .padding(start = 19.dp)
                        .padding(end = 9.dp)
                        .size(4.dp)
                ) {
                    drawCircle(Black)
                }
                Text(
                    text = "룡룡님 피드",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {/* doSomething() */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreHoriz,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .padding(end = 25.dp)
                            .size(16.dp),
                        tint = main_gray2
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
              //  contentPadding = PaddingValues(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { ReviewItem() }
                item { ReviewItem() }
                item { ReviewItem() }
                item { ReviewItem() }
                item { ReviewItem() }
                item { ReviewItem() }
                item { ReviewItem() }
            }
            }
        }
}

@Preview(showBackground = true)
@Composable
fun UserFeedScreenPreview() {
    DVIDETheme {
        UserFeedScreen()
    }
}