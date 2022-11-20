package com.d_vide.D_VIDE.app.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R

@Composable
fun DivideImage(
    modifier: Modifier,
    imageURL: String,
    placeholder: Int = R.drawable.food,
    alpha: Float = 1f,
    contentDescription: String = ""
){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        placeholder = painterResource(placeholder),
        contentScale = ContentScale.Crop,
        modifier = modifier,
        alpha = alpha
    )
}