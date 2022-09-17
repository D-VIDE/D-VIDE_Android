package com.d_vide.D_VIDE.app.presentation.Reviews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetRecommend
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetReviews
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getReviewsUseCase: GetReviews,
    private val getRecommendUseCase: GetRecommend
): ViewModel() {

    private var _state = MutableStateFlow(ReviewsState())
    val state = _state

    init{
        getReviews( latitude = 37.49015482509, longitude = 127.030767490, first = 1)
        getRecommend()
    }

    private fun getReviews(longitude: Double, latitude: Double, first: Int){
        getReviewsUseCase(longitude, latitude, first).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(reviews = result.data?.reviews ?: emptyList(), isLoading = false)
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
}