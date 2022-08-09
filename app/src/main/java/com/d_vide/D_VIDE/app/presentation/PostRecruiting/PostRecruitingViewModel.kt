package com.d_vide.D_VIDE.app.presentation.PostRecruiting

import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.dto.RecruitingBodyDTO
import com.d_vide.D_VIDE.app.domain.use_case.PostRecruiting
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostRecruitingViewModel @Inject constructor(
    val postRecruitingUseCase: PostRecruiting
) : ViewModel() {
    var imageUri = mutableStateOf<Uri?>(null)

    val pathfinder = CameraPosition(LatLng(35.232234, 129.085211), 17f, 1.0f, 0f)
    private val _cameraPositionState = mutableStateOf(CameraPositionState(pathfinder))
    var cameraPositionState: State<CameraPositionState> = _cameraPositionState

    private var _postId = mutableStateOf(0)
    val postId: State<Int> = _postId

    suspend fun postRecruiting(recruitingBody: RecruitingBodyDTO) {
        postRecruitingUseCase(userId = 1, recruitingBody).collectLatest { it ->
            it.data!!.postId.also { _postId.value = it }
        }
    }

}
