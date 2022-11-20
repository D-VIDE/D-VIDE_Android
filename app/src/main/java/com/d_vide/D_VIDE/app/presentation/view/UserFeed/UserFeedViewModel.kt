package com.d_vide.D_VIDE.app.presentation.view.UserFeed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetOtherReviews
import com.d_vide.D_VIDE.app.domain.use_case.User.GetOtherUserInfo
import com.d_vide.D_VIDE.app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFeedViewModel @Inject constructor(
    private val getOtherReviewUseCase: GetOtherReviews,
    private val getOtherUserInfoUseCase: GetOtherUserInfo,
): ViewModel() {
    var userReviews by mutableStateOf(UserReviewsState(isLoading = true))
    var userProfile by mutableStateOf(UserProfileState(isLoading = true))

    fun getOtherUserInfo(userId: Long) {
        viewModelScope.launch {
            getOtherUserInfoUseCase(userId).collect { result ->

                when (result) {
                    is Resource.Success -> {
                        userProfile = result.data?.let {
                            UserProfileState(userProfile = it, isLoading = false)
                        }!!
                    }
                    is Resource.Error -> {
                        userProfile = UserProfileState(error = result.message!!)
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        userProfile = UserProfileState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }

    fun getOtherUserReviews(userId: Long) {
        viewModelScope.launch {
            getOtherReviewUseCase(0, userId).collect { result ->

                when (result) {
                    is Resource.Success -> {
                        userReviews = result.data?.let {
                            UserReviewsState(userReviews = it, isLoading = false)
                        }!!
                    }
                    is Resource.Error -> {
                        userReviews = UserReviewsState(error = result.message!!)
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        userReviews = UserReviewsState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }
}

