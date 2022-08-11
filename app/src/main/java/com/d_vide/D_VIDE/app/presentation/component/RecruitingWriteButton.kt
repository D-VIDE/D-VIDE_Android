package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.app._constants.UIConst
import com.d_vide.D_VIDE.ui.theme.TextStyles
import com.d_vide.D_VIDE.ui.theme.main2
import com.d_vide.D_VIDE.ui.theme.mainOrange

@Composable
fun RecruitingWriteButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    shouldShowBottomBar: Boolean = false
){
    Column (
        modifier = Modifier.fillMaxWidth()
            ){
        FloatingActionButton(
            onClick = onClick,
            backgroundColor = main2,
            modifier = modifier.align(Start).padding(start = 30.dp).size(121.dp, 50.dp),
            shape = RoundedCornerShape(25.dp),
            content = {
                Row(
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.divided),
                        contentDescription = "null",
                        modifier = Modifier.size(25.dp, 28.dp).align(CenterVertically)
                    )
                    Text(
                        text = "글 작성",
                        style = TextStyles.Basics5,
                        color = White,
                        modifier = Modifier.padding(start = 7.dp).align(CenterVertically)
                    )
                }

            }
        )
        if (shouldShowBottomBar)
            Spacer(Modifier.padding(bottom = UIConst.UIConstant.HEIGHT_BOTTOM_BAR))
    }
}

@Preview
@Composable
fun previewbutton() {
    RecruitingWriteButton(modifier = Modifier)
}