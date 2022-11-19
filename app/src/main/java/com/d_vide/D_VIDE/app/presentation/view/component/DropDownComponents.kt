package com.d_vide.D_VIDE.app.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._enums.Category
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun DropDownComp(
    isDropDownMenuExpanded: Boolean,
    onCheckedChange: () -> Unit,
    selectedText: String,
) {
    EditableTextField(
        inputText = selectedText,
        readOnly = true,
        contentAlignment = Alignment.CenterEnd,
    ) {
        IconToggleButton(
            modifier = Modifier.size(36.dp),
            checked = isDropDownMenuExpanded,
            onCheckedChange = { onCheckedChange() },
        ) {
            Image(
                painterResource(id = R.drawable.dropdown_button),
                contentDescription = "content description",
                modifier = Modifier.size(36.dp),
            )
        }

    }
}

/**
 * click된 category state필요
 */

@Composable
fun ExpandedCategory(
    modifier: Modifier = Modifier.fillMaxWidth(),
    currentTag: Category,
    onTagClick: (Category) -> Unit,
) {
    Row(Modifier.padding(bottom = 12.dp)) {
        Spacer(
            modifier = Modifier
                .padding(start = 99.dp)
                .height(80.dp)
        )
        Surface(
            color = Color(0xFFEFEFF0),
            modifier = Modifier
                .fillMaxWidth(1f)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 18.dp),
                    clip = true
                )
        ) {
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 50.dp)
                    .padding(horizontal = 10.dp)
            ) {
                Category::class.sealedSubclasses.mapNotNull { it.objectInstance }.forEach {
                    if (currentTag == it) ItemTag(it, true, onTagClick)
                    else ItemTag(it, false, onTagClick)
                }
            }
        }
    }
}


@Composable
private fun ItemTag(
    category: Category = Category.ALL,
    isClicked: Boolean = false,
    onClick: (Category) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isClicked) mainOrange else Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .clickable { onClick(category) },
    ) {
        Text(
            text = "${category.name}",
            color = if (isClicked) Color.White
            else Color(0xFF8D8D8D),
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}