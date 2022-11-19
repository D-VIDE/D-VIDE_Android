package com.d_vide.D_VIDE.app.presentation.view.MyReviews

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d_vide.D_VIDE.app.domain.use_case.Review.GetMyReviews
import com.d_vide.D_VIDE.app.domain.util.Resource
import com.d_vide.D_VIDE.app.domain.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyReviewsViewModel @Inject constructor(
    private val getMyReviewsUseCase: GetMyReviews,
) : ViewModel() {

    private val _state = mutableStateOf(MyReviewsState())
    val state: State<MyReviewsState> = _state

    init {
        getMyOrders()
    }

    fun getMyOrders() {
        viewModelScope.launch {
            getMyReviewsUseCase(0).collect() {
                when (it) {
                    is Resource.Success -> {
                        _state.value =
                            it.data?.let { MyReviewsState(reviewsDTO = it.reviews) }!!
                        "내 리뷰 목록 가져오기 성공".log()
                    }
                    is Resource.Error -> "내 리뷰 목록 가져오기 실패".log()
                    is Resource.Loading -> "내 리뷰 목록 가져오는 중".log()
                }
            }
        }
    }
}