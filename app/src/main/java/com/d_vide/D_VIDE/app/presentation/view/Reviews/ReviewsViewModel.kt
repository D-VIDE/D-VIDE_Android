package com.d_vide.D_VIDE.app.presentation.view.Reviews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetRecommend
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetReviews
import com.d_vide.D_VIDE.app.domain.use_case.Review.PostLike
import com.d_vide.D_VIDE.app.domain.use_case.Review.PostUnlike
import com.d_vide.D_VIDE.app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getReviewsUseCase: GetReviews,
    private val getRecommendUseCase: GetRecommend,
    private val postLikeUseCase: PostLike,
    private val postUnlikeUseCase: PostUnlike
): ViewModel() {

    private var _state = MutableStateFlow(ReviewsState())
    val state = _state

    init{
        getReviews( latitude = 37.49015482509, longitude = 127.030767490)
        getRecommend()
    }

    fun getReviews(
        longitude: Double = 127.030767490,
        latitude: Double = 37.49015482509
    ){
        viewModelScope.launch {
            getReviewsUseCase(longitude, latitude, _state.value.offset).collect() { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                reviews = it.reviews + (result.data?.reviews ?: emptyList()),
                                isLoading = false,
                                offset = it.offset + (result.data?.reviews?.size ?: 0),
                                pagingLoading = false,
                                endReached = false
                            )
                        }
                        Log.d("가희", "리뷰 호출 ${result.data?.reviews ?: emptyList()}")
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message ?: "An unexpected error occured",
                                isLoading = false,
                                pagingLoading = false,
                                endReached = true
                            )
                        }
                        Log.d("test", "error")
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true,
                                endReached = false
                            )
                        }
                        Log.d("test", "loading")
                    }
                }

            }
        }
    }

    private fun getRecommend(){
        getRecommendUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(recommendStore = result.data?.data ?: emptyList(), isLoading = false)
                    }
                }
                is Resource.Error -> {
                    _state.value = ReviewsState(error = result.message ?: "An unexpected error occured")
                    Log.d("test", "error")
                }
                is Resource.Loading -> {
                    _state.value = ReviewsState(isLoading = true)
                    Log.d("test", "loading")
                }
            }
        }.launchIn(viewModelScope)
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