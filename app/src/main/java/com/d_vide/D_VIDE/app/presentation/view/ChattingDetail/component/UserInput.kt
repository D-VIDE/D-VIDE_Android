package com.d_vide.D_VIDE.app.presentation.ChattingDetail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.ui.theme.*

enum class InputSelector {
    NONE,
    CAMERA,
    BILL
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    resetScroll: () -> Unit = {},
    modifier: Modifier = Modifier,
    btnClick: () -> Unit = {},
    isExpand: Boolean
) {
    var currentInputSelector by rememberSaveable { mutableStateOf(InputSelector.NONE) }
    val dismissKeyboard = { currentInputSelector = InputSelector.NONE }

    var textState by remember { mutableStateOf(TextFieldValue()) }


    // Used to decide if the keyboard should be shown
    var textFieldFocusState by remember { mutableStateOf(false) }

    val plusButtonColors = ButtonDefaults.buttonColors(
        containerColor = if(isExpand) main2 else gray0,
        contentColor = if(isExpand) gray7 else gray2
    )
    Surface(
        modifier = Modifier.padding(horizontal = 20.dp),
        tonalElevation = 2.dp,
        color = gray7,
    ) {
        Row() {
            Button(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .width(42.dp)
                ,
                onClick = btnClick,
                colors = plusButtonColors,
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "추가"
                )
            }


            Box(modifier = modifier.background(gray0, RoundedCornerShape(20.dp))) {
                UserInputText(
                    textFieldValue = textState,
                    onTextChanged = { textState = it },
                    // Only show the keyboard if there's no input selector and text field has focus
                    keyboardShown = currentInputSelector == InputSelector.NONE && textFieldFocusState,
                    // Close extended selector if text field receives focus
                    onTextFieldFocused = { focused ->
                        if (focused) {
                            currentInputSelector = InputSelector.NONE
                            resetScroll()
                        }
                        textFieldFocusState = focused
                    },
                    focusState = textFieldFocusState,
                    modifier = Modifier
                )

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = main1,
                    contentColor = gray7
                )

                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp)
                        .width(42.dp)
                    ,
                    enabled = textState.text.isNotBlank(),
                    onClick = {
                        onMessageSent(textState.text)
                        // Reset text field and close keyboard
                        textState = TextFieldValue()
                        // Move scroll to bottom
                        resetScroll()
                        dismissKeyboard()
                    },
                    colors = buttonColors,
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = "채팅 전송"
                    )
                }

            }
        }
    }
}
private fun TextFieldValue.addText(newString: String): TextFieldValue {
    val newText = this.text.replaceRange(
        this.selection.start,
        this.selection.end,
        newString
    )
    val newSelection = TextRange(
        start = newText.length,
        end = newText.length
    )

    return this.copy(text = newText, selection = newSelection)
}

@ExperimentalFoundationApi
@Composable
private fun UserInputText(
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (TextFieldValue) -> Unit,
    textFieldValue: TextFieldValue,
    keyboardShown: Boolean,
    onTextFieldFocused: (Boolean) -> Unit,
    focusState: Boolean,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Surface(
            color = Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Bottom)
            ) {
                var lastFocusState by remember { mutableStateOf(false) }
                BasicTextField(
                    value = textFieldValue,
                    onValueChange = { onTextChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(end = 50.dp, start = 20.dp, top = 10.dp, bottom = 10.dp)
                        .align(Alignment.CenterStart)
                        .onFocusChanged { state ->
                            if (lastFocusState != state.isFocused) {
                                onTextFieldFocused(state.isFocused)
                            }
                            lastFocusState = state.isFocused
                        },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = ImeAction.Send
                    ),
                    cursorBrush = SolidColor(LocalContentColor.current),
                    textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
                )
            }
        }
    }
}

