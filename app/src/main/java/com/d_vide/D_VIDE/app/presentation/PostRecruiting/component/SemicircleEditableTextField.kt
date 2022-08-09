package com.d_vide.D_VIDE.app.presentation.PostRecruiting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.TextStyles.Basics5
import com.d_vide.D_VIDE.ui.theme.mainOrange
import com.d_vide.D_VIDE.ui.theme.main_gray3


@Composable
fun EditableTextField(
    modifier: Modifier = Modifier,
    inputText: String? = null,
    unitText: String? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    height: Dp = 60.dp,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var focusRequester = remember { FocusRequester() }
    Box(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 0.dp),
                clip = true
            )
        ,
        contentAlignment = contentAlignment
    ) {
        DivideOutlinedTextField(
            readOnly = readOnly,
            value = if(inputText.isNullOrBlank()) text else inputText,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(color = Color.White)
                .padding(0.dp)
                .focusRequester(focusRequester)
            ,
            singleLine = singleLine,
            enabled = enabled,
            shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 0.dp),
        )

        if(!unitText.isNullOrBlank()){
            Text(
                text = unitText,
                style = TextStyles.Point2,
                color = Color(0xFF777777),
//                fontWeight = FontWeight.ExtraBold,
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
    height: Dp = 36.dp,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .height(height)
            .fillMaxWidth()
        ,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 6.dp, top = 8.dp)
                .width(93.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = "Add note",
                modifier = Modifier
                    .size(12.dp)
                    .padding(end = 5.dp)
                    .fillMaxSize(),
                tint = main_gray3
            )
            Text(
                text = labelText,
                color = main_gray3,
                style = Basics5,
                fontWeight = FontWeight.ExtraBold,
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
            labelText = "ABD몸객긴",
        ){
            EditableTextField(contentAlignment = Alignment.CenterEnd) {
                Image(
                    painterResource(id = R.drawable.dropdown_button),
                    contentDescription = "content description",
                    modifier = Modifier.size(36.dp),
                )
            }
        }

        EditableFieldItem(
            labelText = "인원",
        ){
            EditableTextField(unitText = "원") {
            }
        }


        EditableFieldItem(height= 350.dp, labelText = "카테고리"){
            EditableTextField(singleLine = false) {}
        }

        EditableFieldItem(height= 400.dp, labelText = "카테고리"){
            Box(modifier = Modifier.fillMaxSize()) {
                Text("Hello", modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}