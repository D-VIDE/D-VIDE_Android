package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsScreen
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun RecruitingCategory(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .height(26.dp)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
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
            drawPath(path, color = if (isSelected) mainOrange else category_gray)

        }
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .matchParentSize()
                .padding(12.dp, 5.dp),
            fontSize = 12.sp,
            color = if (isSelected) Color.White else recruit_city
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DVIDETheme {
        RecruitingCategory("분식", true)

    }
}