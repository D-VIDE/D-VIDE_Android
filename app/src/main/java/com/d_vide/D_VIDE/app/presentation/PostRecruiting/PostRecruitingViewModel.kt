package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class PostRecruitingViewModel () : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

}
