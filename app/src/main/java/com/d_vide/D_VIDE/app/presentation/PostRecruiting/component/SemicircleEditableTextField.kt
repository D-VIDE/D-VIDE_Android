package com.d_vide.D_VIDE.app.presentation.PostRecruiting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.mainOrange


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
                .background(color = Color(0xFFFFFFFF))
                .padding(0.dp)
                .focusRequester(focusRequester)
            ,
            singleLine = singleLine,
            enabled = enabled,
            shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 0.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = mainOrange,
                cursorColor = mainOrange
            ),
        )

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

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

@Composable
fun DivideOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    @OptIn(ExperimentalMaterialApi::class)
    BasicTextField(
        value = value,
        modifier = modifier
            .background(colors.backgroundColor(enabled).value, shape)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            ),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                border = {
                    TextFieldDefaults.BorderBox(
                        enabled,
                        isError,
                        interactionSource,
                        colors,
                        shape
                    )
                },
                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 10.dp)
            )
        }
    )
}

@OptIn(ExperimentalTextApi::class)
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
//        verticalAlignment = Alignment.CenterVertically
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
                tint = Color(0xFF777777)
            )
            Text(
                text = labelText,
                color = Color(0xFF777777),
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