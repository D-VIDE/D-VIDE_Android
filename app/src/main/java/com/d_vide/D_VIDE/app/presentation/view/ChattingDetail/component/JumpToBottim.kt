package com.d_vide.D_VIDE.app.presentation.view.ChattingDetail.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.*

private enum class Visibility {
    VISIBLE,
    GONE
}

/**
 * Shows a button that lets the user scroll to the bottom.
 */
@Composable
fun JumpToBottom(
    enabled: Boolean,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Show Jump to Bottom button
    val transition = updateTransition(if (enabled) Visibility.VISIBLE else Visibility.GONE)
    val bottomOffset by transition.animateDp() {
        if (it == Visibility.GONE) {
            (-32).dp
        } else {
            32.dp
        }
    }
    if (bottomOffset > 0.dp) {
        FloatingActionButton(
            onClick = onClicked,
            containerColor = main0,
            contentColor = gray7,
            modifier = modifier
                .offset(x = -16.dp, y = -bottomOffset),
            shape = CircleShape,
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowDownward,
                    modifier = Modifier.height(18.dp),
                    contentDescription = null
                )
            }
        )

    }
}

@Preview
@Composable
fun JumpToBottomPreview() {
    JumpToBottom(enabled = true, onClicked = {})
}
