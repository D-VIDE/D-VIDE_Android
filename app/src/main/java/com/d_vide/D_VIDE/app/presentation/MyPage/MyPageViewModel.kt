package com.d_vide.D_VIDE.app.presentation.MyPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserDTO
import com.d_vide.D_VIDE.app.domain.use_case.GetBadges
import com.d_vide.D_VIDE.app.domain.use_case.GetUserInfo
import com.d_vide.D_VIDE.app.domain.use_case.PostBadge
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getuserInfoUseCase: GetUserInfo,
    val getBadgesUseCase: GetBadges,
    val postBadgeUseCase: PostBadge
) : ViewModel() {

    private val _state = MutableStateFlow(MyPageState())
    val state = _state

    private val _userInfo = mutableStateOf(UserDTO())
    var userInfo: State<UserDTO> = _userInfo

    init {
        getUserInfo()
        getBadges()
    }

    private fun getUserInfo() {

        getuserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(userDTO = result.data!!, isLoading = false)
                    }
                    "내 정보 가져오기 성공".log()
                }
                is Resource.Error -> "내 정보 가져오기 실패".log()
                is Resource.Loading -> "내 정보 가져오는 중".log()
            }
        }.launchIn(viewModelScope)
    }
    private fun getBadges(){

        getBadgesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update{
                        it.copy(badgesDTO = result.data?.badges!!.plus(BadgeDTO("")))
                    }

                    "뱃지 가져오기 성공".log()
                }
                is Resource.Error -> "뱃지 가져오기 실패".log()
                is Resource.Loading -> "뱃지 가져오는 중".log()
            }
        }.launchIn(viewModelScope)

    }
    fun postBadges(badgeName: String){
        val badgeRequestDTO = BadgeRequestDTO(badgeName)
        postBadgeUseCase(badgeRequestDTO).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value =
                        it.data?.let { MyPageState(badgeRequestDTO = it) }!!
                    "뱃지 등록하기 성공".log()
                }
                is Resource.Error -> "뱃지 등록하기 실패".log()
                is Resource.Loading -> "뱃지 등록하는 중".log()
            }
        }.launchIn(viewModelScope)
    }

}