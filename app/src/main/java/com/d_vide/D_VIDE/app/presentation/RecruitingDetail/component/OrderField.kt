package com.d_vide.D_VIDE.app.presentation.RecruitingDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.DivideOutlinedTextField
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.main0
import com.d_vide.D_VIDE.ui.theme.main1
import kotlin.math.pow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OrderFormField(
    inputText: String = "",
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
    unitText: String? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    height: Dp = 60.dp,
    onValueChange: (String) -> Unit = {},
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit = {}
) {
    val kc = LocalSoftwareKeyboardController.current
    var result by remember { mutableStateOf("") }
    val callback = {
        result = try {
            val num = inputText.toFloat()
            num.pow(2.0F).toString()
        } catch (ex: NumberFormatException) {
            ""
        }
        kc?.hide()
    }
    Box(
        modifier = modifier.fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                clip = true
            )
        ,
        contentAlignment = contentAlignment
    ) {
        DivideOutlinedTextField(
            readOnly = readOnly,
            value = inputText,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(color = Color.White)
                .padding(end = 20.dp)
            ,
            textStyle = TextStyles.Big1_1,
            singleLine = singleLine,
            enabled = enabled,
            shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                cursorColor = main1,
                unfocusedBorderColor = Color.White,
                textColor = main1
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    callback()
                }
            ),
        )

        if(!unitText.isNullOrBlank()){
            Text(
                text = unitText,
                style = TextStyles.Point2,
                color  = main0,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 13.dp, top = 3.dp)
            )
        }
        content()
    }

}
