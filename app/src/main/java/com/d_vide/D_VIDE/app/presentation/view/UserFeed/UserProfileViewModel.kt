package com.d_vide.D_VIDE.app.presentation.view.UserFeed

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.User.GetOtherUserInfo
import com.d_vide.D_VIDE.app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getOtherUserInfoUseCase: GetOtherUserInfo,
): ViewModel() {

    private var _userProfile = mutableStateOf(UserProfileState(isLoading = true))
    var userProfile: State<UserProfileState> = _userProfile

//    init {
//        getOtherUserInfo(1L)
//    }

    fun getOtherUserInfo(userId: Long) {
        viewModelScope.launch {
            getOtherUserInfoUseCase(userId).collect { result ->

                when (result) {
                    is Resource.Success -> {
                        _userProfile.value = result.data?.let {
                            UserProfileState(userProfile = it, isLoading = false)
                        }!!
                    }
                    is Resource.Error -> {
                        _userProfile.value = UserProfileState(error = result.message!!)
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        _userProfile.value = UserProfileState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }
}

