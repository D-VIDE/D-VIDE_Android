package com.d_vide.D_VIDE.app.presentation.Recruitings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
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
    var selectedItem by remember{ mutableStateOf("")}
    val categoryList = listOf("분식", "한식", "일식", "중식", "양식", "디저트", "피자", "패스트푸드")

    Column(
        modifier = Modifier
            .background(background)
            .verticalScroll(scrollState)
    ){
        TopRoundContainer{
            LazyRow{
                item {
                    Spacer(modifier = Modifier.width(20.dp))
                }
                this.items(items = categoryList) {
                    Row {
                        RecruitingCategory(
                            text = it,
                            isSelected = if (selectedItem == it) true else false,
                            modifier = Modifier
                                .selectable(
                                    selected = selectedItem == it,
                                    onClick = { selectedItem = it }
                                )
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                    }
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