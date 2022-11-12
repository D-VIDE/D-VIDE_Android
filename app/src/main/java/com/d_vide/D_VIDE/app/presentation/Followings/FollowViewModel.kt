package com.d_vide.D_VIDE.app.presentation.Followings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.UserIdDTO
import com.d_vide.D_VIDE.app.domain.use_case.User.DeleteFollow
import com.d_vide.D_VIDE.app.domain.use_case.User.GetFollowInformation
import com.d_vide.D_VIDE.app.domain.use_case.PostFollow
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(
    private val getFollowInfoUseCase: GetFollowInformation,
    private val postFollowUseCase: PostFollow,
    private val deleteFollowUseCase: DeleteFollow
): ViewModel(){

    private var _state = MutableStateFlow(FollowState())
    val state = _state

    private var _userIdDTO = mutableStateOf(
        UserIdDTO(userId = 1L)
    )
    var userIdDTO: State<UserIdDTO> = _userIdDTO

    init{
        getFollowInfo(relation = "FOLLOWER", first=1)
    }

    fun getFollowInfo(relation: String="FOLLOWER", first: Int=1){
        getFollowInfoUseCase(relation, first).onEach{ result ->
            when (result){
                is Resource.Success -> {
                    _state.update{
                        it.copy(follows = result.data?.follows ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    _state.value = FollowState(error = result.message ?: "An unexpected error occured")
                    Log.d("test", "error")
                }
                is Resource.Loading -> {
                    _state.value = FollowState(isLoading = true)
                    Log.d("test", "loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun postFollow(userId: Int){
        _userIdDTO.value = userIdDTO.value.copy(
            userId = userId.toLong()
        )
        postFollowUseCase(_userIdDTO.value).onEach {
            when (it) {
                is Resource.Success -> "팔로잉 성공".log()
                is Resource.Error -> "팔로잉 실패".log()
                is Resource.Loading -> "팔로잉 로딩 중".log()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteFollow(userId: Int){
        _userIdDTO.value = userIdDTO.value.copy(
            userId = userId.toLong()
        )
        deleteFollowUseCase(_userIdDTO.value).onEach {
            when (it) {
                is Resource.Success -> "팔로잉 취소 성공".log()
                is Resource.Error -> "팔로잉 취소 실패".log()
                is Resource.Loading -> "팔로잉 취소 로딩 중".log()
            }
        }.launchIn(viewModelScope)
    }

}