package com.d_vide.D_VIDE.app.presentation.Followings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.UserIdDTO
import com.d_vide.D_VIDE.app.domain.use_case.Follow.DeleteFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetFollowInformation
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetOtherFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.PostFollow
import com.d_vide.D_VIDE.app.domain.use_case.GetOtherUserInfo
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
    private val getOtherFollowUseCase: GetOtherFollow,
    private val postFollowUseCase: PostFollow,
    private val deleteFollowUseCase: DeleteFollow,
): ViewModel(){

    private var _state = MutableStateFlow(FollowState())
    val state = _state

    private var _userIdDTO = mutableStateOf(
        UserIdDTO(userId = 1L)
    )
    var userIdDTO: State<UserIdDTO> = _userIdDTO

    private var _followIdDTO = mutableStateOf(
        FollowIdDTO(followId = 1L)
    )
    var followIdDTO: State<FollowIdDTO> = _followIdDTO

    init{
        getFollowInfo(relation = "FOLLOWER", first=1)
    }

    fun getFollowInfo(relation: String="FOLLOWER", first: Int=0){
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

    fun getOtherFollow(relation: String="FOLLOWER", first: Int=0, userId: Long){
        getOtherFollowUseCase(relation, first, userId).onEach { result ->
            when (result){
                is Resource.Success -> {
                    _state.value = result.data?.let { FollowState(otherFollows = it, isLoading = false)}!!
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
                is Resource.Success -> {
                    _followIdDTO.value = followIdDTO.value.copy(it.data!!.followId)
                    "팔로잉 성공".log()
                }
                is Resource.Error -> "팔로잉 실패".log()
                is Resource.Loading -> "팔로잉 로딩 중".log()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteFollow(followId: Int) {

        _followIdDTO.value = followIdDTO.value.copy(
            followId = followId.toLong()
        )
        deleteFollowUseCase(_followIdDTO.value).onEach {
            when (it) {
                is Resource.Success -> "팔로잉 취소 성공".log()
                is Resource.Error -> "팔로잉 취소 실패".log()
                is Resource.Loading -> "팔로잉 취소 로딩 중".log()
            }
        }.launchIn(viewModelScope)
    }

}