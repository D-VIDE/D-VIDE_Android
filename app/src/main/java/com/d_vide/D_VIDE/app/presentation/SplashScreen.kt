package com.d_vide.D_VIDE.app.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.d_vide.D_VIDE.app._constants.Const.UIConst.DURATION_ANIMATION_SPLASH
import com.d_vide.D_VIDE.app.presentation.Login.KakaoLoginViewModel
import com.d_vide.D_VIDE.app.presentation.Login.LoginViewModel
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.TextStyles.Big1
import com.d_vide.D_VIDE.ui.theme.TextStyles.Point1
import com.d_vide.D_VIDE.ui.theme.main1
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    loginViewModel: KakaoLoginViewModel = hiltViewModel()
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(DURATION_ANIMATION_SPLASH)
    )

    LaunchedEffect(key1 = true) {
        val nextScreen = when (loginViewModel.isLoggedIn()) {
            true -> Screen.HomeScreen
            false -> Screen.SocialLoginScreen
        }
        startAnimation = true

        // 화면 전환
        delay(DURATION_ANIMATION_SPLASH + 500L)
        navController.popBackStack()
        navController.navigate(nextScreen.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
private fun Splash(alpha: Float) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(main1)
            .fillMaxSize()
            .alpha(alpha = alpha)
    ) {
        Text(
            text = "D/VIDE",
            color = Color.White,
            style = Big1,
            modifier = Modifier
                .padding(top = 140.dp)
                .height(95.dp)
        )
        Text(
            text = "지역기반 음식배달 공동주문 소셜 플랫폼",
            color = Color.White,
            style = Point1
        )
    }
}