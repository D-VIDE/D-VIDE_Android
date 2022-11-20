package com.d_vide.D_VIDE.app.presentation.view.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d_vide.D_VIDE.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoPicker(
    @DrawableRes iconId: Int?,
    uri: Uri? = null,
    onGetContent: (Uri) -> Unit = {},
    onLongClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var showMenu by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> uri?.let { onGetContent(it) } }

    Row(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri ?: iconId)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = painterResource(R.drawable.add_photo),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(shape = RoundedCornerShape(15.dp))
                .combinedClickable(
                    onClick = { launcher.launch("image/*") },
                    onLongClick = { showMenu = true },
                )
        )
    }
    PopupMenu(
        item = "삭제",
        onClickCallbacks = onLongClick,
        showMenu = showMenu,
        onDismiss = { showMenu = false }
    )
}

@Composable
fun PopupMenu(
    item: String,
    onClickCallbacks: () -> Unit,
    showMenu: Boolean,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { onDismiss() },
    ) {
        DropdownMenuItem(onClick = {
            onDismiss()
            onClickCallbacks()
        }) { Text(text = item) }
    }
}