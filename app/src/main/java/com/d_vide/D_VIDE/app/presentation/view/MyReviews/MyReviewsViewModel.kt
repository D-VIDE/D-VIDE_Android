package com.d_vide.D_VIDE.app.presentation.view.MyReviews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetMyReviews
import com.d_vide.D_VIDE.app.domain.use_case.Review.PostLike
import com.d_vide.D_VIDE.app.domain.use_case.Review.PostUnlike
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
class MyReviewsViewModel @Inject constructor(
    private val getMyReviewsUseCase: GetMyReviews,
    private val postLikeUseCase: PostLike,
    private val postUnlikeUseCase: PostUnlike
) : ViewModel() {

    private val _state = MutableStateFlow(MyReviewsState())
    val state =  _state

    init {
        getMyOrders()
    }

    fun getMyOrders() {
        viewModelScope.launch {
            getMyReviewsUseCase(_state.value.offset).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                reviews = it.reviews + (result.data?.reviews ?: emptyList()),
                                isLoading = false,
                                offset = it.offset + (result.data?.reviews?.size ?: 0),
                                pagingLoading = false,
                                endReached = result.data?.reviews?.size!! < 10
                            )
                        }
                        Log.d("가희", "나의 리뷰 :${_state.value.offset}")
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

                        "내 리뷰 목록 가져오기 실패".log()
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true
                            )
                        }
                        "내 리뷰 목록 가져오는 중".log()
                    }
                }
            }
        }
    }

    fun postLike(index: Int){
        _state.value.reviews[index].review.isLiked = !_state.value.reviews[index].review.isLiked

        postLikeUseCase(state.value.reviews[index].review.reviewId).onEach {
            when (it) {
                is Resource.Success -> Log.d("스크랩", "스크랩 성공")
                is Resource.Error -> Log.d("스크랩", "스크랩 실패")
                is Resource.Loading -> Log.d("스크랩", "스크랩 올리는중")
            }
        }.launchIn(viewModelScope)
    }
    fun postUnlike(index: Int){
        _state.value.reviews[index].review.isLiked = !_state.value.reviews[index].review.isLiked

        postUnlikeUseCase(state.value.reviews[index].review.reviewId).onEach {
            Log.d("스크랩", "스크랩 2")
            when (it) {
                is Resource.Success -> Log.d("스크랩", "스크랩 취소 성공")
                is Resource.Error -> Log.d("스크랩", "스크랩 취소 실패")
                is Resource.Loading -> Log.d("스크랩", "스크랩 취소 올리는중")
            }
        }.launchIn(viewModelScope)
    }
}