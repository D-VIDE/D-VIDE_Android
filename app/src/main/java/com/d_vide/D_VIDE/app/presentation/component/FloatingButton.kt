package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun FloatingButton(
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 20.sp
) {
    ExtendedFloatingActionButton(
        text = { FABTextContent(text, fontSize) },
        backgroundColor = mainOrange,
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.9f),
        shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 100))
    )
}

@Composable
fun FABTextContent(
    text: String = "",
    fontSize: TextUnit
) {
    Text(
        text = text,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFFFFFFFF),
        fontSize = fontSize
    )
}

@Preview
@Composable
fun PreviewFloatingButton() {
    FloatingButton(text = "지금 D/VIDE 하기", onClick = {} )
}