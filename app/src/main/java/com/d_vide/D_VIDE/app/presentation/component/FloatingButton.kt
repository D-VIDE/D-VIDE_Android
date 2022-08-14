package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.app._constants.Const
import com.d_vide.D_VIDE.ui.theme.main2
@Composable
fun FloatingButton(
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 20.sp,
    shouldShowBottomBar: Boolean = false
) {
    Column() {
        ExtendedFloatingActionButton(
            text = { FABTextContent(text, fontSize) },
            backgroundColor = main2,
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = MaterialTheme.shapes.large.copy(CornerSize(percent = 100))
        )
        if(shouldShowBottomBar)
            Spacer(Modifier.padding(bottom = Const.UIConst.HEIGHT_BOTTOM_BAR))
    }
}

@Composable
fun FABTextContent(
    text: String = "",
    fontSize: TextUnit,
) {
    Text(
        text = text,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        fontSize = fontSize
    )
}

@Preview
@Composable
fun PreviewFloatingButton() {
    FloatingButton(text = "지금 D/VIDE 하기", onClick = {})
}