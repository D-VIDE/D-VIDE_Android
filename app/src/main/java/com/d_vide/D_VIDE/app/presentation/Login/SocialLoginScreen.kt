package com.d_vide.D_VIDE.app.presentation.Login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import com.d_vide.D_VIDE.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun SocialLoginScreen(
    navController: NavController,
    kakaoViewModel: KakaoLoginViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 47.dp, start = 39.dp, end = 31.dp)
                .verticalScroll(scrollState)
        ) {
            LoginImage()
            LoginButton(
                onClick = { kakaoViewModel.handleKakaoLogin() },
                text = "카카오 로그인",
                resId = R.drawable.kakao_logo,
                color = Color(0xFFFEE500),
                textColor = Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            LoginButton(
                onClick = {/*do somehting*/ },
                text = "애플로 로그인",
                resId = R.drawable.apple_logo,
                color = Black,
                textColor = White
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom = 3.dp),
                text = "고객센터 문의하기",
                style = TextStyles.Small2,
                color = gray2,
            )
            Divider(
                modifier = Modifier
                    .size(110.dp, 1.dp)
                    .align(CenterHorizontally), color = gray4
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        Image(
            modifier = Modifier
                .height(109.5.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.login_dividers),
            contentDescription = "login_dividers"
        )
    }
}

@Composable
fun LoginImage() {
    Column {
        Divider(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(39.dp, 8.dp),
            color = main0
        )
        Box {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 48.dp)
                    .size(232.dp, 69.5.dp),
                painter = painterResource(id = R.drawable.login_message),
                contentDescription = "login_message"
            )
            Text(
                text = "나와 디바이더가 되어\n" +
                        "나누러 가볼까?",
                style = TextStyles.Intro,
                color = gray3,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
            )
        }
        Image(
            modifier = Modifier
                .padding(top = 21.dp, bottom = 42.dp)
                .align(End),
            painter = painterResource(id = R.drawable.login_character),
            contentDescription = "login_character"
        )
    }
}

@Composable
fun LoginButton(
    onClick: () -> Unit = {},
    text: String = "",
    textColor: Color = Color(0xFFFFFFFF),
    color: Color = Color(0xFFFFFFFF),
    @DrawableRes resId: Int
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        shape = RoundedCornerShape(26.dp),
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .height(45.dp)
                .padding(end = 7.dp)
                .fillMaxWidth()
                .background(color)
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .height(16.dp)
                    .align(CenterStart),
                painter = painterResource(resId),
                contentDescription = "login_button"
            )
            Text(
                text = text,
                style = TextStyles.LoginText,
                modifier = Modifier.align(Center),
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun PreviewSocialLoginScreen() {
    DVIDETheme {
        SocialLoginScreen(rememberNavController())
    }
}