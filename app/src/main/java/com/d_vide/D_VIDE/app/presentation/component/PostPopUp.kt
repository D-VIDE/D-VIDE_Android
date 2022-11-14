package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.main_gray3

@Composable
fun PostPopUp(
    userName: String = "뇽뇽",
    onDismiss:() -> Unit = {}
){

    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .size(268.dp, 178.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.character_circle),
                contentDescription = "character image",
                modifier = Modifier.size(82.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${userName}님의 글이 업로드 되었어요!",
                style = TextStyles.Point3,
                color = main_gray3
            )
        }
    }
}