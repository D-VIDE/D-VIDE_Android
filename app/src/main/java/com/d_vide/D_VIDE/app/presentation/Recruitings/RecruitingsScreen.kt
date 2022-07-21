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
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer
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
        TopRoundContainer{
            Row(
                modifier = Modifier.padding(15.dp)
            ) {
                val categoryList = listOf("분식", "한식", "일식", "중식", "디저트", "아시안")
                repeat(categoryList.size) {
                    RecruitingCategory(categoryList.get(it))
                    Spacer(Modifier.width(8.dp))
                }
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