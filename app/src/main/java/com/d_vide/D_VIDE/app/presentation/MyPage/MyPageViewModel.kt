package com.d_vide.D_VIDE.app.presentation.MyPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.domain.use_case.Login.GetUserID
import com.d_vide.D_VIDE.app.domain.use_case.User.GetUserInfo
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getUserInfoUseCase: GetUserInfo,
) : ViewModel() {

    private val _state = mutableStateOf(MyPageState())
    val state: State<MyPageState> = _state

    private val _userInfo = mutableStateOf(UserDTO())
    var userInfo: State<UserDTO> = _userInfo

    init {
        getUserInfo()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect() {
                when (it) {
                    is Resource.Success -> {
                        _state.value =
                            it.data?.let { MyPageState(userDTO = it) }!!
                        "내 정보 가져오기 성공".log()
                    }
                    is Resource.Error -> "내 정보 가져오기 실패".log()
                    is Resource.Loading -> "내 정보 가져오는 중".log()
                }
            }
        }
    }
}