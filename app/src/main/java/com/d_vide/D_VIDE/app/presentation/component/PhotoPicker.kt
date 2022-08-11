package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R

@Composable
fun PhotoPicker(
    @DrawableRes iconId: Int,
//    viewModel: PostRecruitingViewModel
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }
    var isSelected by remember { mutableStateOf(false) }

    Row() {
        Button(
            onClick = {
                isSelected = true
                launcher.launch("image/*")
            },
            modifier = Modifier
                .width(80.dp)
                .height(75.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0x00000000)
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        if (isSelected) imageUri
                        else R.drawable.add_photo
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(iconId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp, 75.dp)
                    .clip(shape = RoundedCornerShape(15.dp))

            )
        }
        Spacer(modifier = Modifier.padding(start = 10.dp))
    }
}