package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.app.presentation.util.NavigateButton
import com.d_vide.D_VIDE.ui.theme.*
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.main2

@Composable
fun TopRoundBar(
    text: String = "TopAppBar",
    actions: @Composable RowScope.() -> Unit = {},
    onClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = SdSamlipHopang,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp
                ),
                color = main2
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onClick,
                modifier = Modifier.padding(start = 20.dp).size(11.dp, 19.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.up_press_gray),
                    contentDescription = "뒤로가기",
                )
            }
        },
        actions = actions,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(0.dp, 0.dp, 26.dp, 26.dp),
                clip = true
            ),
    )
}

@Composable
fun TopRoundTextContainer(
    text: String = "D/VIDE"
) {
    TopRoundContainer {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontFamily = SdSamlipHopang,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp
                ),
                color = main2
            )
        }
    }
}

@Composable
fun TopRoundContainer(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(26.dp), clip = false),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {

            val cornerRadius = CornerRadius(26.dp.toPx(), 26.dp.toPx())
            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(
                            offset = Offset(0f, 0f),
                            size = size,
                        ),
                        bottomLeft = cornerRadius,
                        bottomRight = cornerRadius,
                    )
                )
            }
            drawPath(path, color = Color.White)
        }
        content()
    }
}

@Composable
fun TopRoundBarWithImage(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.review_title,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
            painterResource(id = image),
            contentDescription = "review_title",
            modifier = Modifier
                .width(128.dp)
                .height(29.dp)
        ) },
        actions = actions,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(0.dp, 0.dp, 26.dp, 26.dp),
                clip = true
            ),
    )
}

@Composable
fun TopRectangleBar(
    imageURL: String = "",
    title: String = "",
    upPress: () -> Unit
){
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .height(55.dp)
            .fillMaxWidth()
            .background(main1)
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigateButton(tint = gray7, onClick = upPress)


        DivideImage(
            modifier = Modifier
                .size(37.dp)
                .clip(CircleShape),
            imageURL = imageURL,
            placeholder = R.drawable.icon_profile
        )


        Text(title, style = TextStyles.Basics5, color = gray7)
    }
}

@Preview
@Composable
fun PreviewTopRoundContainer() {
    Column {
        TopRoundContainer{}
        TopRoundContainer(){Text("hello")}
        TopRoundTextContainer("D/VIDE 모집글 작성")
        TopRoundBar("D/VIDE 모집글 작성")
    }

}