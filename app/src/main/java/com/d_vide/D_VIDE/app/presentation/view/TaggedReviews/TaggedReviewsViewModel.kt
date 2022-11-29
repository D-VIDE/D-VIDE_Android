package com.d_vide.D_VIDE.app.presentation.view.TaggedReviews

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetStoreReview
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.navigation.DetailDestinationKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaggedReviewsViewModel @Inject constructor(
    private val getStoreReviewUseCase: GetStoreReview,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val storeName = savedStateHandle.get<String>(DetailDestinationKey.TAGGEDREVIEW)!!

    private val _state = MutableStateFlow(TaggedReviewsState())
    val state = _state

    init{
        getReviews()
    }

    fun getReviews(){
        viewModelScope.launch {
            getStoreReviewUseCase(_state.value.offset, storeName).collect() { result ->
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
                        Log.d("가희", "식당 리뷰 :${_state.value.offset}")
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

                        "식당 리뷰 가져오기 실패".log()
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true,
                                pagingLoading = true
                            )
                        }
                        "식당 리뷰 목록 가져오는 중".log()
                    }
                }

            }
        }
    }
}