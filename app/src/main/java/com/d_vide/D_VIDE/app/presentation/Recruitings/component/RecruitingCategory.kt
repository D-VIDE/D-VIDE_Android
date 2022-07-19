package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.recruit_city

@Composable
fun RecruitingCategory(
    text: String
){
    val is_clicked = remember { mutableStateOf(false)}
    Box(
        modifier = Modifier
            .width(54.dp)
            .height(29.dp)
            .clickable {
                is_clicked.value = !is_clicked.value
                //TODO
            }
    ) {
        Canvas(
            modifier = Modifier
                .width(54.dp)
                .height(29.dp)
        ) {

            val cornerRadius = CornerRadius(26.dp.toPx(), 26.dp.toPx())
            val path = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(
                            offset = Offset(0f, 0f),
                            size = size,
                        ),
                        cornerRadius = cornerRadius
                    )
                )
            }
            drawPath(path, color = if (is_clicked.value) mainOrange else background)

        }
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .padding(2.dp),
            color = if (is_clicked.value) Color.White else recruit_city
        )
    }
}
