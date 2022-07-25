package com.d_vide.D_VIDE.app.presentation.PostRecruiting.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.ui.theme.mainOrange


@Composable
fun EditableTextField(
    inputText: String? = null,
    unitText: String? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    height: Dp = 60.dp,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var focusRequester = remember { FocusRequester() }
    Box(
        modifier = Modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp),
                clip = true
            )
        ,
        contentAlignment = contentAlignment
    ) {
        OutlinedTextField(
            readOnly = readOnly,
            value = if(inputText.isNullOrBlank()) text else inputText,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(height)
                .background(color = Color(0xFFFFFFFF))
                .padding(0.dp)
                .focusRequester(focusRequester)
            ,
            singleLine = singleLine,
            shape = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = mainOrange,
                cursorColor = mainOrange
            )
        )

        LaunchedEffect(Unit) { focusRequester.requestFocus() }

        if(!unitText.isNullOrBlank()){
            Text(
                text = unitText,
                color = Color(0xFF919191),
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp)
            )
        }
        content()
    }

}

@Composable
fun EditableFieldItem(
    labelText: String,
    height: Dp = 60.dp,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .padding(vertical = 5.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .width(100.dp)
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = "Add note",
                modifier = Modifier
                    .size(12.dp)
                    .padding(end = 5.dp)
                    .fillMaxSize(),
                tint = Color.Gray
            )
            Text(
                text = labelText,
                color = Color.Gray,
                fontWeight = FontWeight.ExtraBold
            )
        }
        content()
    }
}

@Preview
@Composable
fun PreviewSemicircleEditableTextField() {
    Column() {
        EditableFieldItem(
            labelText = "제목",
        ){}

        EditableFieldItem(
            labelText = "인원",
        ){
            EditableTextField(unitText = "원") {
            }
        }

        EditableFieldItem(labelText = "카테고리"){
            Surface(
                shape = RoundedCornerShape(100),
                color = Color(0xFF797979),
                modifier = Modifier.align(Alignment.End)
            ) {}
        }
    }

}