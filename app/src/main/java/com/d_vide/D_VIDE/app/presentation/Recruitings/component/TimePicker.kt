package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import java.util.*

@Composable
fun TimePicker() {
    val mContext = LocalContext.current
    val mHour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
    val mMinute = Calendar.getInstance()[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour 시 $mMinute 분"
        }, mHour, mMinute, false
    )

    EditableTextField(
        modifier = Modifier.clickable(onClick = { mTimePickerDialog.show() }),
        inputText = mTime.value,
        readOnly = true,
        enabled = false
    ) {}
}