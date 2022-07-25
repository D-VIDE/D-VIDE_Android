package com.d_vide.D_VIDE.app.presentation.Recruitings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R
import com.d_vide.D_VIDE.ui.theme.DVIDETheme

@Composable
fun BlankIndicator(
    modifier: Modifier = Modifier
){
    Box(
      modifier = modifier
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painterResource(id = R.drawable.blank_indicator),
                contentDescription = "blank_indicator",
                modifier = Modifier.size(82.9.dp, 82.33.dp)
            )
            Text(
                text = "아직 새로운 모집글이\n업로드 되지 않았어요",
                modifier = Modifier.padding(top = 11.67.dp),
                textAlign = TextAlign.Center,
                color = Color(0xFFB0B0B0),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlankIndicatorPreview() {
    DVIDETheme {
        BlankIndicator( )
    }
}