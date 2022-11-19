package com.d_vide.D_VIDE.app.presentation.view.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.ui.theme.gray6
import com.d_vide.D_VIDE.ui.theme.main1

@Composable
fun DivideButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.height(50.dp).fillMaxWidth(),
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(100),
    border: BorderStroke? = null,
    backgroundColor: Color = main1,
    contentColor: Color = gray6,
    contentPadding: PaddingValues = PaddingValues(vertical = 6.dp, horizontal = 8.dp),
    content: @Composable RowScope. () -> Unit
){
    Surface(
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = border,
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .clickable(
                onClick = onClick,
                enabled = enabled
            )

    ) {
        Row (
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                )
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}




@Composable
@Preview
fun PreviewDivideButton(){
    DivideButton(onClick = { /*TODO*/ }) {
        Text(text = "test")
    }
}