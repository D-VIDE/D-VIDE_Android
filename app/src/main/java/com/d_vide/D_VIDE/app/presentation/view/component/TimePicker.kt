package com.d_vide.D_VIDE.app.presentation.view.component

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.d_vide.D_VIDE.app.presentation.util.getCurrentDay
import com.d_vide.D_VIDE.app.presentation.util.getCurrentMonth
import com.d_vide.D_VIDE.app.presentation.util.getCurrentYear
import com.d_vide.D_VIDE.app.presentation.view.PostRecruiting.PostRecruitingViewModel
import com.d_vide.D_VIDE.app.presentation.view.PostRecruiting.PostRecruitingsEvent
import java.util.*

@Composable
fun TimePicker(
    viewModel: PostRecruitingViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    val mHour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
    val mMinute = Calendar.getInstance()[Calendar.MINUTE]
    val mTime = remember { mutableStateOf("") }
    var calendar = remember{ mutableStateOf( GregorianCalendar() ) }


    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = if(mHour > 12) "오후 ${mHour-12} 시 $mMinute 분" else "오전 $mHour 시 $mMinute 분"
            calendar.value = GregorianCalendar(getCurrentYear(), getCurrentMonth(), getCurrentDay(), mHour, mMinute)
        }, mHour, mMinute, false
    )


    viewModel.onEvent(PostRecruitingsEvent.EnteredTargetTime(calendar.value.timeInMillis / 1000 + 60*60*9))

    EditableTextField(
        modifier = Modifier.clickable(onClick = { mTimePickerDialog.show() }),
        inputText = mTime.value,
        readOnly = true,
        enabled = false
    ) {}
}