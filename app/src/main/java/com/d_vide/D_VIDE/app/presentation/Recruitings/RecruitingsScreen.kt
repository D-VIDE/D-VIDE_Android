package com.d_vide.D_VIDE.app.presentation.Recruitings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingCategory
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.RecruitingItem
import com.d_vide.D_VIDE.ui.theme.DVIDETheme
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.graph_gray
import com.d_vide.D_VIDE.ui.theme.mainYellow

@Composable
fun RecruitingsScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(background)
            .verticalScroll(scrollState)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(26.dp), clip = false)
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

            Row(
                modifier = Modifier.padding(15.dp)
            ) {
                RecruitingCategory("분식")
                Spacer(Modifier.width(8.dp))
                RecruitingCategory("한식")
                Spacer(Modifier.width(8.dp))
                RecruitingCategory("일식")
                Spacer(Modifier.width(8.dp))
                RecruitingCategory("중식")
                Spacer(Modifier.width(8.dp))
                RecruitingCategory("디저트")
                Spacer(Modifier.width(8.dp))
                RecruitingCategory("아시안")
            }
        }
        Column(
            modifier = Modifier.padding(start = 22.dp)
        ){
            Spacer(Modifier.height(5.dp))
            RecruitingItem()
            Spacer(Modifier.height(15.dp))
            RecruitingItem()
            Spacer(Modifier.height(15.dp))
            RecruitingItem()
        }


    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DVIDETheme {
        RecruitingsScreen()
    }
}