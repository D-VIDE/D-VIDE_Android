package com.d_vide.D_VIDE.app.presentation.view.UserFeed

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetOtherReviews
import com.d_vide.D_VIDE.app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReviewsViewModel @Inject constructor(
    private val getOtherReviewUseCase: GetOtherReviews,
): ViewModel() {
    var userReviews by mutableStateOf(UserReviewsState())

//    init {
//        getOtherUserReviews(1L)
//    }

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

