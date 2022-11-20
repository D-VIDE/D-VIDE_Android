package com.d_vide.D_VIDE.app.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.ui.theme.gray1


@Composable
fun DivideDivider(modifier: Modifier = Modifier, alpha: Float = 1f){
    Divider(
        modifier = modifier,
        color = gray1.copy(alpha = alpha),
        thickness = 1.dp
    )
}