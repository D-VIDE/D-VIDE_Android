package com.d_vide.D_VIDE.app.presentation.MyPage

import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.BadgeRequestDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.BadgeDTO
import com.d_vide.D_VIDE.app.domain.use_case.GetBadges
import com.d_vide.D_VIDE.app.domain.use_case.PostBadge
import com.d_vide.D_VIDE.app.domain.use_case.User.GetUserInfo
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    val getBadgesUseCase: GetBadges,
    val postBadgeUseCase: PostBadge,
    val getUserInfoUseCase: GetUserInfo,
) : ViewModel() {

    val state by mutableStateOf(MyPageState())

    init {
        getUserInfo()
        getBadges()
    }

    /*
    private fun counterPolicy(): SnapshotMutationPolicy<MyPageState?> =
        object : SnapshotMutationPolicy<MyPageState?> {
            override fun equivalent(a: MyPageState?, b: MyPageState?): Boolean {
                if(a?.userDTO!!.badge == b?.userDTO!!.badge) return a.userDTO.badge == b.userDTO.badge
                return a == b
            }
            override fun merge(
                previous: MyPageState?,
                current: MyPageState?,
                applied: MyPageState?
            ): MyPageState? = previous
        }
    */

    private fun getUserInfo() {

        getUserInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    state.userDTO = result.data!!

                    "내 정보 가져오기 성공".log()
                }
                is Resource.Error -> "내 정보 가져오기 실패".log()
                is Resource.Loading -> "내 정보 가져오는 중".log()
            }
        }.launchIn(viewModelScope)
    }

    private fun getBadges() {

        getBadgesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                    state.badgesDTO = result.data!!.badges

//                    _state.update {
//                        it.copy(badgesDTO = result.data?.badges!!.plus(BadgeDTO("")))
//                    }

                    "뱃지 가져오기 성공".log()
                }
                is Resource.Error -> "뱃지 가져오기 실패".log()
                is Resource.Loading -> "뱃지 가져오는 중".log()
            }
        }.launchIn(viewModelScope)

    }

    fun postBadges(badgeName: String) {
        val badgeRequestDTO = BadgeRequestDTO(badgeName)
        postBadgeUseCase(badgeRequestDTO).onEach {
            when (it) {
                is Resource.Success -> {
                    state.badgeRequestDTO = it.data!!
                    "뱃지 등록하기 성공".log()
                }
                is Resource.Error -> "뱃지 등록하기 실패".log()
                is Resource.Loading -> "뱃지 등록하는 중".log()
            }
        }.launchIn(viewModelScope)
    }

}