package com.d_vide.D_VIDE.app.presentation.RecruitingDetail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.DivideOutlinedTextField
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.main0

@Composable
fun OrderFormField(
    inputText: String = "",
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    unitText: String? = null,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    height: Dp = 60.dp,
    onValueChange: (String) -> Unit = {},
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit = {}
) {
    var focusRequester = remember { FocusRequester() }
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
                .padding(0.dp)
                .focusRequester(focusRequester)
            ,
            singleLine = singleLine,
            enabled = enabled,
            shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
            keyboardOptions = keyboardOptions
        )

        if(!unitText.isNullOrBlank()){
            Text(
                text = unitText,
                style = TextStyles.Point2,
                color  = main0,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 13.dp)
            )
        }
        content()
    }

}
