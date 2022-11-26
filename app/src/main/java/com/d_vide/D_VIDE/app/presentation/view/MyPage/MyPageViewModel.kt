package com.d_vide.D_VIDE.app.presentation.view.MyPage

import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.domain.use_case.GetBadges
import com.d_vide.D_VIDE.app.domain.use_case.PostBadge
import com.d_vide.D_VIDE.app.domain.use_case.User.GetUserInfo
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.state.UserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getBadgesUseCase: GetBadges,
    val postBadgeUseCase: PostBadge,
    val getUserInfoUseCase: GetUserInfo,
) : ViewModel() {
    var state by mutableStateOf(MyPageState())
        private set
    var badge by mutableStateOf("")
        private set

    var user by mutableStateOf(UserInformation.userInfo)
        private set

    init {
        getUserInfo()
        getBadges()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        badge = UserInformation.userInfo.badge.name

                        "내 정보 가져오기 성공".log()
                    }
                    is Resource.Error -> "내 정보 가져오기 실패".log()
                    is Resource.Loading -> "내 정보 가져오는 중".log()
                }
            }
        }
    }

    fun getBadges() {
        viewModelScope.launch {
            getBadgesUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(badgesDTO = result.data!!.badges)
                        "뱃지 가져오기 성공".log()
                    }
                    is Resource.Error -> "뱃지 가져오기 실패".log()
                    is Resource.Loading -> "뱃지 가져오는 중".log()
                }
            }
        }
    }

    fun postBadges(badgeName: String) {
        val badgeRequestDTO = BadgeRequestDTO(badgeName)
        viewModelScope.launch {
            postBadgeUseCase(badgeRequestDTO).collect {
                when (it) {
                    is Resource.Success -> {
                        getUserInfo()
                        "뱃지 등록하기 성공".log()
                    }
                    is Resource.Error -> {
                        getUserInfo()
                        "뱃지 등록하기 실패".log()
                    }
                    is Resource.Loading -> "뱃지 등록하는 중".log()
                }
            }
        }
    }

}