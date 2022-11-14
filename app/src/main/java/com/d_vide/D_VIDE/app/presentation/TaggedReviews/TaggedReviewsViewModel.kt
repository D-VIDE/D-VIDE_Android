package com.d_vide.D_VIDE.app.presentation.TaggedReviews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.data.remote.responseDTO.Review.StoreReviewsDTO
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetReviews
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetStoreReview
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.presentation.Reviews.ReviewsState
import com.d_vide.D_VIDE.app.presentation.navigation.DetailDestinationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TaggedReviewsViewModel @Inject constructor(
    private val getStoreReviewUseCase: GetStoreReview,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val storeName = savedStateHandle.get<String>(DetailDestinationKey.TAGGEDREVIEW)!!

    private val _state = mutableStateOf(TaggedReviewsState())
    val state: State<TaggedReviewsState> = _state

    init{
        getReviews(0, storeName)
    }

    private fun getReviews(first: Int = 0, storeName: String){
        getStoreReviewUseCase(first, storeName).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = result.data?.let { TaggedReviewsState(reviews = result.data.reviews) }!!
                }
                is Resource.Error -> {
                    _state.value = TaggedReviewsState(error = result.message ?: "An unexpected error occured")
                    Log.d("test", "error")
                }
                is Resource.Loading -> {
                    _state.value = TaggedReviewsState(isLoading = true)
                    Log.d("test", "loading")
                }
            }

        }.launchIn(viewModelScope)
    }
}