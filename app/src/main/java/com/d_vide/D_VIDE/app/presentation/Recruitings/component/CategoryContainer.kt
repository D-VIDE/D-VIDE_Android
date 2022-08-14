package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.app.presentation.component.TopRoundContainer

@Composable
fun CategoryContainer(
    onCategoryChange: (Category) -> Unit
){
    var selectedItem by remember { mutableStateOf("") }

    TopRoundContainer {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            item {
                Spacer(modifier = Modifier.width(11.dp))
            }
            item {
                Category::class.sealedSubclasses.mapNotNull { it.objectInstance }.forEach {
                    Row {
                        RecruitingCategory(
                            text = it.name,
                            isSelected = selectedItem == it.tag,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .selectable(
                                    selected = selectedItem == it.tag,
                                    onClick = {
                                        selectedItem = it.tag
                                        onCategoryChange(it)
                                    },
                                )
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                    }
                }
            }
        }
    }
}