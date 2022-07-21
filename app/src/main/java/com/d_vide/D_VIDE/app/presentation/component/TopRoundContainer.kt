package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.ui.theme.mainOrange

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
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = mainOrange,
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

@Preview
@Composable
fun PreviewTopRoundContainer() {
    Column() {
        TopRoundContainer{}
        TopRoundContainer(){Text("hello")}
        TopRoundTextContainer("D/VIDE 모집글 작성")
    }

}