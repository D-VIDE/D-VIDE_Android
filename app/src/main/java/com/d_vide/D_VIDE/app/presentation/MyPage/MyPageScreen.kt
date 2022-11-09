package com.d_vide.D_VIDE.app.presentation.MyPage

import android.app.Dialog
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.component.DivideImage
import com.d_vide.D_VIDE.app.presentation.navigation.NavGraph
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.formatAmountOrMessage
import com.d_vide.D_VIDE.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.absoluteValue

@Composable
fun MyPageScreen(
    navController: NavController,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val viewModelState = viewModel.state.value.userDTO
    val scrollState = rememberScrollState()
    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        viewModel.getUserInfo()
    }
    Box(modifier = Modifier.background(background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .zIndex(2F)
                .verticalScroll(scrollState)
            ,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyPageUserProfile(
                username = viewModelState.nickname,
                badges = viewModelState.badge.name,
                followerCount = viewModelState.followerCount,
                followingCount = viewModelState.followingCount,
                image = viewModelState.profileImgUrl,
                onFollowerClick = { navController.navigate("${Screen.MyFollowScreen.route}/false") },
                onFollowingClick = { navController.navigate("${Screen.MyFollowScreen.route}/true")}
            )
            MyPageSavings(viewModelState.savedPrice)
            MyPageCommonCell("나의 주문내역 보기") {
                navController.navigate(NavGraph.MYREVIEW)
            }
            MyPageCommonCell("내가 쓴 리뷰 보기"){
                navController.navigate(Screen.MyReviewsScreen.route)
            }
            MyPageCommonCell("고객센터로 이동 "){
                navController.navigate(Screen.PostReviewScreen.route)
            }
        }
        BackgroundImage(Modifier.align(Alignment.BottomEnd))
    }

}

@Composable
fun BackgroundImage(
    modifier: Modifier = Modifier
) {
    Image(
        painterResource(id = R.drawable.character_d),
        contentDescription = "character_circle",
        modifier = modifier
            .padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR)
            .size(200.dp)
            .zIndex(1F)
            .clipToBounds()
            .offset(x = (90).dp)
        ,
        alpha = 0.2F
    )
}

@Composable
fun MyPageCommonCell(
    text : String,
    onClick : () -> Unit = {}
) {
    CardContainer(onClick = onClick) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = mainOrange,
                modifier = Modifier.align(Alignment.Center)
            )
            Icon(
                modifier = Modifier
                    .size(11.dp)
                    .align(Alignment.CenterEnd),
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "arrow",
                tint = mainOrange
            )
        }
    }
}

@Composable
fun MyPageSavings(
    savedPrice: Int = 0
) {
    CardContainer(verticalPadding = 25.dp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("절약한 탄소배출", fontSize = 12.sp)
            Divider(modifier = Modifier.size(0.dp, 12.dp), )
            Text("절약한 배달비", fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.size(15.dp))
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.size(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(formatAmountOrMessage(savedPrice.toString())+" 원", fontSize = 22.sp, color = mainOrange, fontWeight = FontWeight.ExtraBold)
            Divider(
                modifier = Modifier.size(1.dp, 31.dp),
                color = Color.Gray,
            )
            Text(formatAmountOrMessage((savedPrice*0.6).toInt().toString())+" 원", fontSize = 22.sp, color = mainOrange, fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun MyPageUserProfile(
    username: String = "디바이드 공식 돼지",
    badges: String = "",
    followerCount: Int = 0,
    followingCount: Int = 0,
    image: String = "",
    onFollowerClick: () -> Unit,
    onFollowingClick: () -> Unit
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    Box(contentAlignment = Alignment.TopCenter) {
        UserProfileImage(imageUrl = image)
        Image(
            painter = painterResource(id = R.drawable.setting),
            contentDescription = "setting",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(23.dp)
                .clickable { isDialogOpen.value = !isDialogOpen.value }
        )
        Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 55.dp)
                .border(2.dp, mainYellow, RoundedCornerShape(14.dp))
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.size(55.dp))
                Text(
                    text = badges,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = mainOrange,
                    modifier = Modifier.padding(top = 3.dp)
                )
                Text(
                    text = username,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Following(
                    onFollowerClick = onFollowerClick,
                    onFollowingClick = onFollowingClick,
                    Follower = followerCount,
                    Following = followingCount
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
    if (isDialogOpen.value)
        BadgeDialog(onDismiss = {isDialogOpen.value = !isDialogOpen.value})
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BadgeDialog(
    onDismiss:() -> Unit = {}
){
    val list = mutableListOf("인싸 디바이더", "디바이드 공식 돼지", "꿀꿀냠냠", "디바이드 공식 돼지", "꿀꿀냠냠", "")

    val pagerState = rememberPagerState(initialPage = 1)

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.setting_unselected),
                    contentDescription = "setting",
                    modifier = Modifier
                        .padding(top = 7.dp)
                        .padding(10.dp)
                        .size(23.dp)
                        .clickable(onClick = onDismiss)
                        .align(CenterEnd)
                )
            }
            Spacer(Modifier.clickable(onClick = onDismiss).padding(bottom = 100.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(31.dp)
                    .clip(RoundedCornerShape(15.5.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                HorizontalPager(
                    count = list.size,
                    contentPadding = PaddingValues(start = 213.dp),
                    state = pagerState
                ) { page ->
                    Text(
                        text = list[page],
                        style = if (page == pagerState.currentPage - 1) TextStyles.Point2 else TextStyles.Small3,
                        color = if (page == pagerState.currentPage - 1) mainYellow else main_gray2,
                        textAlign = TextAlign.Center,
                    )
                }
                Box(
                    modifier = Modifier
                        .border(4.dp, mainYellow, RoundedCornerShape(19.5.dp))
                        .size(140.dp, 50.dp)
                )
            }
            Spacer(Modifier.clickable(onClick = onDismiss).padding(bottom = 500.dp))
        }
    }
}

@Composable
fun UserProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String
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
            .size(107.dp)
            .shadow(elevation = 5.dp, shape = CircleShape)
            .zIndex(1F),
    )
}

@Composable
fun Following(
    modifier: Modifier = Modifier,
    onFollowerClick: () -> Unit = {},
    onFollowingClick: () -> Unit = {},
    Following: Int = 6,
    Follower: Int = 3
){
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = onFollowingClick),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = formatAmountOrMessage(Following.toString()),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    color = Color(0xFF4D4D4D)
                )
                Text(
                    text = "팔로잉",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Divider(
                modifier = Modifier.size(1.dp, 31.dp),
                color = Color.Gray,
            )
            Column(
                modifier = Modifier
                    .clickable(onClick = onFollowerClick)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = formatAmountOrMessage(Follower.toString()),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontSize = 20.sp,
                    color = Color(0xFF4D4D4D),
                )
                Text(
                    text = "팔로워",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun CardContainer(
    verticalPadding: Dp = 15.dp,
    ColumnModifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(26.dp), clip = true)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = ColumnModifier) {
            Spacer(modifier = Modifier.size(verticalPadding))
            content()
            Spacer(modifier = Modifier.size(verticalPadding))
        }
    }
}

@Preview
@Composable
private fun PreviewMyPageScreen() {
    MyPageScreen(navController = rememberNavController())
}
