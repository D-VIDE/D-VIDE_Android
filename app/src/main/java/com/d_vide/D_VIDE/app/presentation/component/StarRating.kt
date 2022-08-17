package com.d_vide.D_VIDE.app.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.d_vide.D_VIDE.R
import androidx.compose.ui.unit.dp
import com.d_vide.D_VIDE.ui.theme.gray8
import com.d_vide.D_VIDE.ui.theme.main0

@Composable
fun StarRating(
    rating: Double,
    onValueChange: (Double) -> Unit
){

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        for(i in 1..5){
            Icon(
                painter = painterResource(id = R.drawable.icon_star_gray),
                contentDescription = "star",
                tint = if( i <= rating) main0 else gray8,
                modifier = Modifier.clickable{
                    onValueChange(i.toDouble())
                }
            )
        }
    }
}