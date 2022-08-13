package com.d_vide.D_VIDE.app.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.gray8


/**
 * 이 형식으로 사용할 떄 마다 추가해주소
 */
@Composable
fun NavigateButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    tint: Color = gray8
){
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIos,
            contentDescription = "뒤로가기",
            tint = tint
        )
    }
}



@Composable
fun MoreButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    tint: Color = gray8
){
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "더보기",
            tint = tint
        )
    }
}

@Composable
fun LikeButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    isClicked: Boolean = false
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(if(isClicked)R.drawable.icon_like else R.drawable.icon_nonlike),
            contentDescription = "like",
            modifier = modifier
        )
    }
}
