package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.util.coloredShadow
import com.d_vide.D_VIDE.ui.theme.gray5
import com.d_vide.D_VIDE.ui.theme.gray7

@Composable
fun CardTopRound(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp),
    color: Color = gray7,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled
            )
            .coloredShadow(
                color = gray5,
                alpha = 0.15f,
                shadowRadius = 1.dp,
                offsetX = 0.dp,
                offsetY = (-1).dp,
                borderRadius = 26.dp,
            )
            .fillMaxWidth()
            .height(121.dp),
        color = color,
        shape = shape,
        content = content
    )
}

@Composable
fun CardEndRound(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(topEnd = 26.dp, bottomEnd = 26.dp),
    color: Color = gray7,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled
            )
            .fillMaxWidth()
            .height(146.dp),
        color = color,
        shape = shape,
        elevation = 5.dp,
        content = content
    )
}

@Composable
fun CardRound(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(26.dp),
    color: Color = gray7,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled
            )
            .fillMaxWidth()
            .height(52.dp),
        color = color,
        shape = shape,
        elevation = 5.dp,
        content = content
    )
}

@Composable
fun CardRoundSmall(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(13.dp),
    color: Color = gray7,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable () -> Unit
){
    Surface(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled
            )
            .size(106.dp, 137.dp),
        color = color,
        shape = shape,
        elevation = 7.dp,
        content = content
    )
}
