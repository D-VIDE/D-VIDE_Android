package com.d_vide.D_VIDE.app.presentation.Reviews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetReviews
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.Recruitings.RecruitingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val getReviewsUseCase: GetReviews
): ViewModel() {
    private val _state = mutableStateOf(ReviewsState())
    val state: State<ReviewsState> = _state

    init{
        getReviews( latitude = 37.49015482509, longitude = 127.030767490, first = 1)
    }

    private fun getReviews(longitude: Double, latitude: Double, first: Int){
        getReviewsUseCase(longitude, latitude, first).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = result.data?.let { ReviewsState(reviews = result.data.reviews) }!!
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