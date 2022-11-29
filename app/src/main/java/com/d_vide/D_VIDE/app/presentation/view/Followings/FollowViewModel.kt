package com.d_vide.D_VIDE.app.presentation.view.Followings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.requestDTO.UserIdDTO
import com.d_vide.D_VIDE.app.data.remote.responseDTO.FollowIdDTO
import com.d_vide.D_VIDE.app.domain.use_case.Follow.DeleteFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetFollowInformation
import com.d_vide.D_VIDE.app.domain.use_case.Follow.GetOtherFollow
import com.d_vide.D_VIDE.app.domain.use_case.Follow.PostFollow
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.state.UserInformation.userInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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

    private var _userIdDTO = mutableStateOf(UserIdDTO(userInfo.userId))
    var userIdDTO: State<UserIdDTO> = _userIdDTO

    private var _followIdDTO = mutableStateOf(FollowIdDTO(userInfo.userId))
    var followIdDTO: State<FollowIdDTO> = _followIdDTO

    fun getFollowInfo(relation: String="FOLLOWER"){
        viewModelScope.launch {
            getFollowInfoUseCase(relation, _state.value.offset).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                follows = it.follows + (result.data?.follows ?: emptyList()),
                                isLoading = false,
                                offset = it.offset + (result.data?.follows?.size ?: 0),
                                pagingLoading = false,
                                endReached = result.data?.follows?.size!! < 10
                            )
                        }
                        Log.d("팔로잉", "성공")
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message ?: "An unexpected error occured in my review",
                                isLoading = false,
                                pagingLoading = false,
                                endReached = true
                            )
                        }
                        Log.d("팔로잉", "error")
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true
                            )
                        }
                        Log.d("팔로잉", "loading")
                    }
                }
            }
        }
    }

    fun getOtherFollow(relation: String="FOLLOWER", userId: Long){
        viewModelScope.launch {
            getOtherFollowUseCase(relation, _state.value.offset, userId).collect() { result ->
                when (result){
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                otherFollows = it.otherFollows + (result.data ?: emptyList()),
                                isLoading = false,
                                offset = it.offset + (result.data?.size ?: 0),
                                pagingLoading = false,
                                endReached = result.data?.size!! < 10
                            )
                        }
                        Log.d("팔로워", "성공 ${_state.value.endReached}")
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message ?: "An unexpected error occured in my review",
                                isLoading = false,
                                pagingLoading = false,
                                endReached = true
                            )
                        }
                        Log.d("팔로워", "error")
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true
                            )
                        }
                        Log.d("팔로워", "loading")
                    }
                }
            }
        }
    }
    fun postFollow(userId: Long){
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