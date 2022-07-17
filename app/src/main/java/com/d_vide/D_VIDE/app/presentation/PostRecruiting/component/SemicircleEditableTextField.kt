package com.d_vide.D_VIDE.app.presentation.PostRecruiting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.ui.theme.background
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun SemicircleEditableTextField(
    labelText: String,
    singleLine: Boolean = true,
    width: Dp = 300.dp,
    height: Dp = 56.dp
) {
    var text by rememberSaveable { mutableStateOf("") }
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(horizontal = 4.dp)
        ,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()

        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = "Add note",
                    modifier = Modifier
                        .size(12.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = labelText,
                )
            }
        }
        OutlinedTextField(
            value = text,
            modifier = Modifier
                .width(width)
                .height(height),
            readOnly = false,
            onValueChange = {
                text = it
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = singleLine
        )

    }
}

@Preview
@Composable
fun PreviewSemicircleEditableTextField() {
    SemicircleEditableTextField(
        labelText = "제목",
    )
}