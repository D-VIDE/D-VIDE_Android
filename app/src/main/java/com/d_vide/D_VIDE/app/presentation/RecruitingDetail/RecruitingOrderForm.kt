package com.d_vide.D_VIDE.app.presentation.RecruitingDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app.presentation.RecruitingDetail.component.OrderFormField
import com.d_vide.D_VIDE.app.presentation.Recruitings.component.PhotoPicker
import com.d_vide.D_VIDE.app.presentation.component.DivideButton
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.gray7
import com.d_vide.D_VIDE.ui.theme.main1

@Composable
fun RecruitingOrderForm(
    modifier: Modifier = Modifier
){
    val scrollableState = rememberScrollState()
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
            .background(White)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .verticalScroll(scrollableState)
    ){
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ){
            Divider(
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 28.dp)
                    .size(35.5.dp, 3.dp)
                    .align(CenterHorizontally)
                    .clip(RoundedCornerShape(1.5.dp)),
                color = Color(0xFFE1E1E1)
            )
            Text(
                text = "주문 메뉴",
                style = TextStyles.Big2,
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = "삼첩분식 (한남점)",
                style = TextStyles.Point4,
                color = main1
            )
            Spacer(modifier = Modifier.height(25.dp))
            LazyRow() {
                item { PhotoPicker(iconId = R.drawable.add_big_image, modifier = Modifier.height(263.dp)) }
                item { Spacer(modifier = Modifier.width(16.dp)) }
                item { PhotoPicker(iconId = R.drawable.add_big_photo, modifier = Modifier.height(263.dp)) }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row {
                Text(
                    text = "총 주문 금액",
                    style = TextStyles.Big2,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(end = 34.dp)
                )
                OrderFormField(
                    unitText = "원"
                )
            }
            Spacer(modifier = Modifier.height(59.dp))

        }
        DivideButton(
            onClick = {

            },
            modifier = Modifier
                .padding(bottom = 50.dp)
                .align(CenterHorizontally)
                .height(50.dp)
                .fillMaxWidth()) {
            Text(text = "D/VIDE!", style = TextStyles.Big1, color = gray7)
        }
    }
}

@Preview
@Composable
fun PreviewRecruitingOrderForm() {
    RecruitingOrderForm(Modifier)
}