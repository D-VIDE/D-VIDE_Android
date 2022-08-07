package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostRecruitingViewModel @Inject constructor() : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

    val pathfinder = CameraPosition(LatLng(35.232234, 129.085211), 17f, 1.0f, 0f)
    private val _cameraPositionState = mutableStateOf(CameraPositionState(pathfinder))
    var cameraPositionState: State<CameraPositionState> = _cameraPositionState

}
