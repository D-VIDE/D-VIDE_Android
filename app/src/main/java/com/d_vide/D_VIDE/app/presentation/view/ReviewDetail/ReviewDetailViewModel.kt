package com.d_vide.D_VIDE.app.presentation.view.ReviewDetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetReviewDetail
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.presentation.navigation.DetailDestinationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewDetailViewModel @Inject constructor(
    private val getReviewDetailUseCase: GetReviewDetail,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    val postId = savedStateHandle.get<Long>(DetailDestinationKey.REVIEW)!!

    private var _reviewDetail = mutableStateOf(ReviewDetailState(isLoading = true))
    var reviewDetail: State<ReviewDetailState> = _reviewDetail

    init{
        getReviewDetail(postId)
    }

    private fun getReviewDetail(postId: Long){
        viewModelScope.launch {
            getReviewDetailUseCase(postId).collect{ result ->

                when(result){
                    is Resource.Success -> {
                        _reviewDetail.value = result.data?.let {
                            ReviewDetailState(reviewDetail = it.data, isLoading = false)
                        }!!
                    }
                    is Resource.Error -> {
                        _reviewDetail.value = ReviewDetailState(error = result.message!!)
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        _reviewDetail.value = ReviewDetailState(isLoading = true)
                        Log.d("test", "loading")
                    }
                }
            }
        }
    }

}